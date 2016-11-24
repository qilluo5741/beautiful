package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IDrawcashStateDao;
import com.xtc.entity.DrawcashState;
import com.xtc.service.IDrawcashStateService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class DrawcashStateServiceImpl implements IDrawcashStateService{
	@Autowired
	private IDrawcashStateDao dao;
	@Override
	public List<DrawcashState> selectAll() {
		return dao.selectAll();
	}

	@Override
	public DrawcashState selectOneBybid(String accid) {
		return dao.selectOneBybid(accid);
	}

	@Override
	public boolean insert(DrawcashState DrawcashState) {
		return dao.insert(DrawcashState);
	}

	@Override
	public boolean updateDrawcashState() {
		return dao.updateDrawcashState();
	}

	@Override
	public boolean deleteDrawcashState(String userid) {
		return dao.deleteDrawcashState(userid);
	}

	@Override
	public DrawcashState selectBydid(String did) {
		return dao.selectBydid(did);
	}
}
