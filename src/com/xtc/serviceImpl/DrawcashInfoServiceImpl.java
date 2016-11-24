package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IDrawcashInfoDao;
import com.xtc.entity.DrawcashInfo;
import com.xtc.service.IDrawcashInfoService;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DrawcashInfoServiceImpl implements IDrawcashInfoService{
	@Autowired
	IDrawcashInfoDao dao;
	@Override
	public List<DrawcashInfo> selectAll() {
		return dao.selectAll();
	}

	@Override
	public DrawcashInfo selectOneBydid(String did) {
		return dao.selectOneBydid(did);
	}

	@Override
	public boolean insert(DrawcashInfo drawcashInfo) {
		return dao.insert(drawcashInfo);
	}

	@Override
	public boolean updateDrawcashInfo() {
		return dao.updateDrawcashInfo();
	}

	@Override
	public boolean deleteDrawcashInfo(String userid) {
		return dao.deleteDrawcashInfo(userid);
	}

	@Override
	public DrawcashInfo selectBybankid(String bankid) {
		return dao.selectBybankid(bankid);
	}

	@Override
	public List<DrawcashInfo> selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public DrawcashInfo selectBydrawid(String drawid) {
		return dao.selectBydrawid(drawid);
	}
	
	
}
