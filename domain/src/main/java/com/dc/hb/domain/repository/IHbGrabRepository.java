package com.dc.hb.domain.repository;

import com.dc.hb.domain.repository.entity.HbGrab;

/*
 *  =======================================
 *  项目：hb
 *  模块：domain
 *  文件：IHbGrabRepository.java
 *  创建：gavin
 *  更新：2021/3/1 下午15:17
 *  =======================================
 */
public interface IHbGrabRepository {

	/**
	 * 报错抢红包记录
	 * @param hbGrab
	 */
	public void save(HbGrab hbGrab);
	
	
	
}
