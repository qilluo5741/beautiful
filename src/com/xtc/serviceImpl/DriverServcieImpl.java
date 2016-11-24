package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IDriverDao;
import com.xtc.entity.Driver;
import com.xtc.service.IDriverService;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DriverServcieImpl implements IDriverService{
	@Autowired
	IDriverDao dao;

	@Override
	public Driver getOne(String userid) {
		return dao.getOne(userid);
	}

	@Override
	public boolean updateDriver(Driver d) {
		return dao.updateDriver(d);
	}

	@Override
	public boolean create(Driver d) {
		return dao.create(d);
	}

}
