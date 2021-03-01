package com.dc.hb.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 *  =======================================
 *  项目：hb
 *  模块：dtos
 *  文件：UserGrabHbReqDTO.java
 *  描述：用户抢红包dto
 *  创建：gavin
 *  更新：2021/3/1 下午15:27
 *  =======================================
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserGrabHbReqDTO extends BaseDTO{
	
	/**
	 * 红包id
	 */
	private Long hbId;
	/**
	 * 用户id
	 */
	private Long userId;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
