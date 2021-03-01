package com.dc.hb.domain.repository.impl;

import org.springframework.stereotype.Component;

import com.dc.hb.common.IdGenerator;
import com.dc.hb.domain.repository.IHbRepository;
import com.dc.hb.domain.repository.entity.HbInfo;

@Component
public class HbRepositoryImpl implements IHbRepository{

	@Override
	public void save(HbInfo hbInfo) {
		// TODO Auto-generated method stub
		hbInfo.setId(IdGenerator.getId(4L, 4L));
	}

	@Override
	public void update(HbInfo hbInfo) {
		// TODO Auto-generated method stub
		
	}

}
