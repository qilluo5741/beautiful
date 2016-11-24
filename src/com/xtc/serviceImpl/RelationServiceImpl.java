package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IrelationDao;
import com.xtc.entity.relation;
import com.xtc.service.IrelationService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class RelationServiceImpl implements IrelationService {
	@Autowired
	private IrelationDao dao;
	@Override
	public List<relation> selectAll() {
		return dao.selectAll();
	}

	@Override
	public boolean insert(relation relation) {
		return dao.insert(relation);
	}

	@Override
	public relation selectinvideCode(String invideCode) {
		return dao.selectinvideCode(invideCode);
	}

	@Override
	public Pager<relation> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex, pageSize);
	}
}
