package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IskatingadDao;
import com.xtc.entity.skatingad;
import com.xtc.service.IskatingadService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class IskatingadServiceImpl implements IskatingadService {
	@Autowired
	private IskatingadDao dao;
	@Override
	public List<skatingad> selectByskatingad() {
		return dao.selectByskatingad();
	}
}
