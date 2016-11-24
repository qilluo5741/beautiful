package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IncreorderDao;
import com.xtc.entity.increorder;
import com.xtc.service.IncreorderService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class IncreorderServiceImpl implements IncreorderService {
	@Autowired
	private IncreorderDao dao;
	@Override
	public boolean Addincreorder(increorder increorder) {
		return dao.Addincreorder(increorder);
	}
	@Override
	public List<Object> Increproperty(String userid, String repmenuid) {
		return dao.Increproperty(userid, repmenuid);
	}
	@Override
	public boolean updateIncreorder(String instatus, String intime,double inordermoney,String inorderNo) {
		return dao.updateIncreorder(instatus, intime,inordermoney,inorderNo);
	}
	@Override
	public  List<increorder> selectUserid(String inorderNo) {
		return dao.selectUserid(inorderNo);
	}
}
