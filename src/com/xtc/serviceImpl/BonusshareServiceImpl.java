package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.BonusshareDao;
import com.xtc.entity.bonusshare;
import com.xtc.service.BonusshareService;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class BonusshareServiceImpl implements BonusshareService {
	@Autowired
	private BonusshareDao dao;
	@Override
	public boolean insert(bonusshare b) {
		return dao.insert(b);
	}
	@Override
	public bonusshare selectByMobile(String mobile,String order_num) {
		return dao.selectByMobile(mobile,order_num);
	}
	@Override
	public boolean updateCount(int bonuscount, String mobile,String bonusdate) {
		return dao.updateCount(bonuscount, mobile,bonusdate);
	}
	@Override
	public int selectbonuscount(String mobile,String bonusdate) {
		return dao.selectbonuscount(mobile,bonusdate);
	}

}
