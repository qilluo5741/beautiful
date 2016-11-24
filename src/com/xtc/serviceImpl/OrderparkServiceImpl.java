package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IorderparkDao;
import com.xtc.entity.orderpark;
import com.xtc.service.IorderparkService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OrderparkServiceImpl implements IorderparkService {
	@Autowired
	private IorderparkDao dao;
	@Override
	public boolean insert(orderpark orderpark) {
		return dao.insert(orderpark);
	}
	@Override
	public boolean updateorderpark(String evaluate, String pinId) {
		return dao.updateorderpark(evaluate, pinId);
	}
	@Override
	public boolean updateorderparkr(String evaluatr, String pinId) {
		return dao.updateorderparkr(evaluatr, pinId);
	}
	@Override
	public List<orderpark> selectBymobile(String usname) {
		return dao.selectBymobile(usname);
	}
}
