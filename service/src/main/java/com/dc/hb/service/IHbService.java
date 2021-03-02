package com.dc.hb.service;

import com.dc.hb.dtos.UserGrabHbReqDTO;
import com.dc.hb.dtos.UserGrabHbRespDTO;
import com.dc.hb.dtos.UserSendHbReqDTO;

/*
 *  =======================================
 *  项目：hb
 *  模块：service
 *  文件：IHbservice.java
 *  创建：gavin
 *  更新：2021/3/1 上午11:23
 *  =======================================
 */
public interface IHbService {

	/**
	 * 用户发送红包
	 * @param dto
	 */
	public void sendHb(UserSendHbReqDTO dto);
	
	/**
	 * 抢红包
	 * @param dto
	 */
	public UserGrabHbRespDTO grabHb(UserGrabHbReqDTO dto);
	
	/**
	 * 红包回收
	 */
	public void returnHb();
}
