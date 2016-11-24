package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.propertyationDao;
import com.xtc.entity.propertyation;
import com.xtc.service.propertyationService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class propertyationServiceImpl implements propertyationService {
	@Autowired
	private propertyationDao dao;
	
	@Override
	public boolean addpropertyation(propertyation property) {
		return dao.addpropertyation(property);
	}

	@Override
	public List<propertyation> selectpropertyation(String parkid) {
		return dao.selectpropertyation(parkid);
	}
}
