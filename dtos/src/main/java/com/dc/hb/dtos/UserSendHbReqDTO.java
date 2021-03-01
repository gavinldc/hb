package com.dc.hb.dtos;

import com.dc.hb.dtos.exception.LogicError;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 *  =======================================
 *  项目：hb
 *  模块：dtos
 *  文件：UserSendHbReqDTO.java
 *  创建：gavin
 *  更新：2021/3/1 上午11:23
 *  =======================================
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSendHbReqDTO extends BaseDTO{
   
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 红包个数
	 */
	private Integer num;
	
	/**
	 * 红包类型
	 */
	private HbTypeEnum type;
	
	/**
	 * 红包金额
	 */
	private Long money;
	
	/**
	 * 红包文字
	 */
	private String description;
	
	/**
	 * 群id
	 */
	private Long groupId;
	
	
	/**
	 * 红包类型枚举
	 */
	public enum HbTypeEnum{
		Normal(0);//普通红包

		private int value;

		private HbTypeEnum(int typeValue) {
			this.value = typeValue;
		}

		@Override
		public String toString() {
			return String.valueOf(this.value);
		}
	}


	@Override
	public boolean validate() {
		if(userId==null) {
		   throw new LogicError("2000", "用户id不能为空");
		}
		if(money/num<1) {
			 throw new LogicError("2000", "红包金额不能小于1分钱");
		}
		//TODO validate...
		return true;
	}
}
