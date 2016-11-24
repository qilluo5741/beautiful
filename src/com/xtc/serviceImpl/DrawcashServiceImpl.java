package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IdrawcashDao;
import com.xtc.entity.drawcash;
import com.xtc.service.IdrawcashService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DrawcashServiceImpl implements IdrawcashService {
	@Autowired
	private IdrawcashDao dao;
	@Override
	public Pager<drawcash> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex, pageSize);
	}
	@Override
	public boolean updateState(String finaltime,String drawid) {
		return dao.updateState(finaltime,drawid);
	}
}
