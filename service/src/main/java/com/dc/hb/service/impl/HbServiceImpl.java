package com.dc.hb.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.hb.common.RedisCache;
import com.dc.hb.domain.remote.IAccountClient;
import com.dc.hb.domain.repository.IHbGrabRepository;
import com.dc.hb.domain.repository.IHbRepository;
import com.dc.hb.domain.repository.entity.HbGrab;
import com.dc.hb.domain.repository.entity.HbInfo;
import com.dc.hb.dtos.UserGrabHbReqDTO;
import com.dc.hb.dtos.UserGrabHbRespDTO;
import com.dc.hb.dtos.UserSendHbReqDTO;
import com.dc.hb.dtos.exception.LogicError;
import com.dc.hb.service.IHbService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HbServiceImpl implements IHbService {

	@Autowired
	private IAccountClient accountClient;
	@Autowired
	private IHbRepository hbRepository;
	@Autowired
	private IHbGrabRepository hbGrabRepository;

	// TODO
	// 服务端放重复处理加锁@DisLock(lockType="sendHb",lockId="#dto.userId",timeout=1000,tryTimes=3)
	@Override
	public void sendHb(UserSendHbReqDTO dto) {

		// 校验余额

		Long balance = accountClient.getBalance(dto.getUserId());
		if (balance < dto.getMoney()) {
			throw new LogicError("1000", "账户余额不足");
		}

		// 先触发账变
		try {
			accountClient.accountEvent(dto.getUserId(), dto.getMoney()*-1, 0, new Date());
		} catch (Throwable e) {
			log.error("账变接口异常，reason：｛｝", e);
			throw new LogicError("9999", "系统异常");
		}

		try {
			// 生成红包
			HbInfo hb = HbInfo.builder().groupId(dto.getGroupId()).createTime(new Date()).currentMoney(dto.getMoney())
					.currentNum(dto.getNum()).description(dto.getDescription())
					.expireTime(System.currentTimeMillis() + 24 * 60 * 60 * 1000l).money(dto.getMoney())
					.num(dto.getNum()).state(0).userId(dto.getUserId()).type(dto.getType().ordinal()).build();
			hbRepository.save(hb);
			// 预处理
			pretreatHb(dto, hb.getId());

		} catch (Throwable e) {
			log.error("发送红包出错：{}",e);
			// 回滚账变
			accountClient.accountEvent(dto.getUserId(), dto.getMoney(), -1, new Date());
			throw e;
		}

		// TODO 异步群发消息

	}

	/**
	 * 红包预处理
	 * 
	 * @param dto
	 */
	private void pretreatHb(UserSendHbReqDTO dto, Long hbId) {

		long temp = dto.getMoney();
		// 红包的金额从1分到平均数的随即数
		// 处理后放入缓存
		for (int i = 0; i < dto.getNum(); i++) {
			Long m = temp / (dto.getNum() - i);
			long ran = new Random().nextInt(m.intValue()) + 1;
			temp = temp - ran;
			RedisCache.lpush("hb.split.".concat(hbId.toString()), ran);
		}

	}

	@Override
	public UserGrabHbRespDTO grabHb(UserGrabHbReqDTO dto) {
		// TODO 校验强红包用户合法性

		UserGrabHbRespDTO resp = new UserGrabHbRespDTO();
		resp.setHbId(dto.getHbId());
		resp.setMoney(0l);
		// 抢过的用户不能再抢
		if (hasGrab(dto)) {
			return resp;
		}

		// 红包剩余个数不足时直接返回
		String splitKey = "hb.split.".concat(dto.getHbId().toString());
		Long count = RedisCache.llen(splitKey);
		if (count < 1) {
			return resp;
		}

		Long money = RedisCache.rpop(splitKey);
		if (money == null || money == 0) {
			return resp;
		}
		try {
			grabTransaction(dto, money);
		} catch (Exception e) {
			// 回滚缓存
			RedisCache.lpush(splitKey, money);
			RedisCache.del("hb.grab.".concat(dto.getHbId().toString()).concat(dto.getUserId().toString()));
			throw e;
		}
		resp.setMoney(money);
		// TODO 异步群发抢红包信息
		return resp;
	}

	// 这里保证事务一致性@Transcation(rollback = Exception.class)
	private void grabTransaction(UserGrabHbReqDTO dto, Long money) {
		// 构建红包记录
		HbGrab record = HbGrab.builder().creatTime(new Date()).hbId(dto.getHbId()).money(money).userId(dto.getUserId())
				.build();
		hbGrabRepository.save(record);
		// 更新红包信息
		HbInfo hb = HbInfo.builder().id(dto.getHbId()).currentMoney(money).build();
		hbRepository.update(hb);
		// 账变
		accountClient.accountEvent(dto.getUserId(), money, 0, new Date());
	}

	/**
	 * 判断用户是否已经强国红包
	 * 
	 * @param dto
	 * @return
	 */
	// TODO
	// 服务端放重复处理加锁@DisLock(lockType="#dto.HbId",lockId="#dto.userId",timeout=1000,tryTimes=3)
	private boolean hasGrab(UserGrabHbReqDTO dto) {
		String key = "hb.grab.".concat(dto.getHbId().toString()).concat(dto.getUserId().toString());
		if (RedisCache.exists(key)) {
			return true;
		}
		RedisCache.setAndExpire(key, 1, 24 * 60 * 60l);
		return false;
	}

}
