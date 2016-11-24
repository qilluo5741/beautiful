package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IjpushinfoDao;
import com.xtc.entity.jpushinfo;
import com.xtc.service.IjpushinfoService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class JpushinfoImpl implements IjpushinfoService {
	@Autowired
	private IjpushinfoDao dao;
	@Override
	public  List<jpushinfo> selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}
	@Override
	public boolean insert(jpushinfo jpush) {
		return dao.insert(jpush);
	}
	@Override
	public boolean updatejpushinfo(String regid, String userid) {
		return dao.updatejpushinfo(regid, userid);
	}
	@Override
	public String selectByregid(String userid) {
		return dao.selectByregid(userid);
	}
}
