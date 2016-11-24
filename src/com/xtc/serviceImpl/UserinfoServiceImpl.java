package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IUserinfoDao;
import com.xtc.entity.Userinfo;
import com.xtc.service.IUserinfoService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UserinfoServiceImpl implements IUserinfoService{
	@Autowired
	private IUserinfoDao dao;

	@Override
	public Userinfo login(String userName, String userPwd) {
		return dao.login(userName, userPwd);
	}
	
}
