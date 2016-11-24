package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IAdviseDao;
import com.xtc.entity.Advise;
import com.xtc.service.IAdviseService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class AdviseServiceImpl implements IAdviseService {
	@Autowired
	private IAdviseDao dao;
	@Override
	public boolean insert(Advise advise) {
		return dao.insert(advise);
	}

}
