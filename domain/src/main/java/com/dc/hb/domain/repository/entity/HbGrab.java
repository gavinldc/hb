package com.dc.hb.domain.repository.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class HbGrab {

	
	private Long id;
	
	private Long hbId;
	
	private Long userId;
	
	private Long money;
	
	private Date creatTime;
	
	
	
}
