package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.tollRecordsDao;
import com.xtc.entity.sysuser;
import com.xtc.entity.tollRecords;
import com.xtc.service.tollRecordsService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class tollRecordsServiceImpl implements tollRecordsService {
	@Autowired
	private tollRecordsDao dao;
	@Override
	public boolean addtollRecords(tollRecords tollrecord) {
		return dao.addtollRecords(tollrecord);
	}
	@Override
	public List<tollRecords> selecttollRecords(String tollRphone) {
		return dao.selecttollRecords(tollRphone);
	}
	@Override
	public boolean updatetollRecords(String password, String mobile) {
		return dao.updatetollRecords(password, mobile);
	}
	@Override
	public String selectBymobile(String mobile) {
		return dao.selectBymobile(mobile);
	}
	@Override
	public String selectBymobiles(String mobile) {
		return dao.selectBymobile(mobile);
	}
	@Override
	public boolean updateTollRecord(String name,String mobile,String password,String age,String is_owner,String is_admin, String first, String property, String isdelete, String parkid) {
		return dao.updateTollRecord(name, mobile, password, age, is_owner, is_admin, first, property, isdelete, parkid);
	}
	@Override
	public boolean delectTollRecord(String isdelete, String mobile) {
		return dao.delectTollRecord(isdelete, mobile);
	}
	@Override
	public sysuser selectByParkid(String mobile) {
		return dao.selectByParkid(mobile);
	}
	@Override
	public boolean deleteTollRecord(String tollRrecphone) {
		return dao.deleteTollRecord(tollRrecphone);
	}
	@Override
	public boolean deleteTollRecords(String mobile) {
		return dao.deleteTollRecords(mobile);
	}
	@Override
	public List<tollRecords> slectTollRecords(String parkid) {
		return dao.slectTollRecords(parkid);
	}
	@Override
	public String selectBymtollRrecphone(String tollRrecphone) {
		return dao.selectBymtollRrecphone(tollRrecphone);
	}
}
