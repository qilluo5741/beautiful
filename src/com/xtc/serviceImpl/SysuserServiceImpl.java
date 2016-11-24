package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.SysuserDao;
import com.xtc.entity.Park;
import com.xtc.entity.sysuser;
import com.xtc.service.SysuserService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class SysuserServiceImpl implements SysuserService {
	@Autowired
	private SysuserDao dao;
	@Override
	public sysuser getsysuserInfo(String mobile, String password) {
		return dao.getsysuserInfo(mobile, password);
	}
	@Override
	public boolean updatesysuserpwd(String first, String password, String mobile) {
		return dao.updatesysuserpwd(first, password, mobile);
	}
	@Override
	public sysuser selectsysuserfirst(String mobile) {
		return dao.selectsysuserfirst(mobile);
	}
	@Override
	public List<Park> selectByParkName(String name) {
		return dao.selectByParkName(name);
	}
	@Override
	public boolean addsysuser(com.xtc.entity.sysuser sysuser) {
		return dao.addsysuser(sysuser);
	}
	@Override
	public sysuser sysusermobile(String mobile) {
		return dao.sysusermobile(mobile);
	}
	@Override
	public String selectmobile(String mobile) {
		return dao.selectmobile(mobile);
	}
	@Override
	public boolean updatesysuserrelation(String parkid,String isdelete,String property,String name, String mobile, String is_admin) {
		return dao.updatesysuserrelation(parkid,isdelete,property,name, mobile, is_admin);
	}
	@Override
	public boolean updateparkuser(String userid,String parkid){
		return dao.updateparkuser(userid,parkid);
	}
	@Override
	public List<Object> selectusermobile(String parkid) {
		return dao.selectusermobile(parkid);
	}
	@Override
	public boolean updatesysuserrelation(String is_admin, String parkId, String mobile) {
		return dao.updatesysuserrelation(is_admin, parkId, mobile);
	}
	@Override
	public boolean updatesysuserrelations(String is_admin, String parkId, String isdelete, String mobile) {
		return dao.updatesysuserrelations(is_admin, parkId, isdelete, mobile);
	}
	@Override
	public List<sysuser> selectsysusermobilet(String parkid) {
		return dao.selectsysusermobilet(parkid);
	}
}
