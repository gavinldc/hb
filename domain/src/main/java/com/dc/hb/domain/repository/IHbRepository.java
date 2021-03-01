package com.dc.hb.domain.repository;

import com.dc.hb.domain.repository.entity.HbInfo;

/*
 *  =======================================
 *  项目：hb
 *  模块：domain
 *  文件：IHbRepository.java
 *  创建：gavin
 *  更新：2021/3/1 下午14:43
 *  =======================================
 */
public interface IHbRepository {

	/**
	 * 保存红包
	 * @param hbInfo
	 */
	public void save(HbInfo hbInfo);
	
	/**
	 * 更新红包信息
	 * @param hbInfo
	 */
	public void update(HbInfo hbInfo);
	
}
