package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IbalancedetailDao;
import com.xtc.entity.balancedetail;
import com.xtc.service.IbalancedetailService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class BalancedetailServiceImpl implements IbalancedetailService {
	@Autowired
	private IbalancedetailDao dao;
	@Override
	public boolean insert(balancedetail balan) {
		return dao.insert(balan);
	}

	@Override
	public List<balancedetail> selectByuserid(String userid,int pageIndex,int pageSize) {
		return dao.selectByuserid(userid,pageIndex,pageSize);
	}

	@Override
	public  Pager<balancedetail> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex,pageSize);
	}

	@Override
	public String selectBybalancetype(String userid) {
		return dao.selectBybalancetype(userid);
	}

	@Override
	public boolean updateBybalancetype(String balancetype, String userid) {
		return dao.updateBybalancetype(balancetype, userid);
	}
}
