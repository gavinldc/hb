package com.dc.hb.domain.repository.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class HbInfo {

	private Long id;
	
	private Long userId;
	
	private Long money;
	
	private Integer num;
	
	private Integer type;
	
	private String description;
	
	private Date createTime;
	
	private Long currentMoney;
	
	private Integer currentNum;
	
	private Long expireTime;
	
	private Integer state;
	
	private Date returnTime;
	
	private Long groupId;
	
	
	
}
