package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IRepairmenuDao;
import com.xtc.entity.Repairmenu;
import com.xtc.service.IRepairmenuService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class RepairmenuServiceImpl implements IRepairmenuService {
	@Autowired
	private IRepairmenuDao dao;
	@Override
	public List<Repairmenu> getRepairmenuAll() {
		return dao.getRepairmenuAll();
	}

}
