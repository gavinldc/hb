package com.dc.hb.dtos;

import lombok.Data;
import lombok.ToString;


/*
 *  =======================================
 *  项目：hb
 *  模块：dtos
 *  文件：UserGrabHbRespDTO.java
 *  创建：gavin
 *  更新：2021/3/1 上午11:23
 *  =======================================
 */
@Data
@ToString
public class UserGrabHbRespDTO {

    /**
     * 红包id
     */
	private Long hbId;
	/**
	 * 抢的金额
	 */
	private Long money;
	
	
	
	
}
