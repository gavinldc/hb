package com.dc.hb.domain.remote;

import java.util.Date;

/*
 *  =======================================
 *  项目：hb
 *  模块：domain
 *  文件：IAccountClient.java
 *  创建：gavin
 *  描述：账户服务接口
 *  更新：2021/3/1 上午13:55
 *  =======================================
 */
public interface IAccountClient {

	/**
	 * 获取账号余额
	 * @param userId
	 * @return
	 */
	public Long getBalance(Long userId);
	
	/**
	 * 账变操作，由服务提供方保证一致性
	 * @param userId
	 * @param money 金额正加，负减
	 * @param operation 操作类型 默认 0 抵消（用在数据回滚）-1
	 * @param time 操作时间
	 */
	public void accountEvent(Long userId,Long money,Integer operation,Date time);
	
}
