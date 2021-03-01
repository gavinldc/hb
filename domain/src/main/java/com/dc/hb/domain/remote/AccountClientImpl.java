package com.dc.hb.domain.remote;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AccountClientImpl implements IAccountClient{

	@Override
	public Long getBalance(Long userId) {
		return 1000000l;
	}

	@Override
	public void accountEvent(Long userId, Long money, Integer operation, Date time) {
		// TODO Auto-generated method stub
		
	}

}
