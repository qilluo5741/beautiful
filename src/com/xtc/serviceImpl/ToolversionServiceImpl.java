package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.ToolversionDao;
import com.xtc.entity.toolversion;
import com.xtc.service.ToolversionService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ToolversionServiceImpl implements ToolversionService {
	@Autowired
	private ToolversionDao dao;
	@Override
	public List<toolversion> selectByToolversion() {
		return dao.selectByToolversion();
	}
}
