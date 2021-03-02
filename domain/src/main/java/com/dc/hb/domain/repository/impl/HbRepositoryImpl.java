package com.dc.hb.domain.repository.impl;

import java.util.List;

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

	@Override
	public List<HbInfo> findExpireHb() {
		// TODO Auto-generated method stub
		return null;
	}

}
