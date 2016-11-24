package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IgoodpropertyDao;
import com.xtc.entity.goodproperty;
import com.xtc.service.IgoodpropertyService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class goodpropertyServiceImpl implements IgoodpropertyService {
	@Autowired
	private IgoodpropertyDao dao;
	@Override
	public List<goodproperty> selectgetgoodpropertyAll(String repmenuid) {
		return dao.selectgetgoodpropertyAll(repmenuid);
	}
}
