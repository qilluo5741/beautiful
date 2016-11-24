package com.xtc.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IUserDao;
import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.information;
import com.xtc.service.IUserService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao dao;
	@Override
	public List<User> selectAll() {
		return dao.selectAll();
	}

	@Override
	public User selectOneByuid(String uid) {
		return dao.selectOneByuid(uid);
	}

	@Override
	public boolean insert(User user) {
		return dao.insert(user);
	}

	@Override
	public boolean updateUser(String mobile,String state)  {
		return dao.updateUser(mobile, state);
	}

	@Override
	public boolean deleteUser(String uid) {
		return dao.deleteUser(uid);
	}

	@Override
	public List<User> selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public List<User> selectBymobil(String mobil) {
		return dao.selectBymobil(mobil);
	}
	public User selectOne(String invideCode){
		return dao.selectOne(invideCode);
	}

	@Override
	public boolean updateInvideCode(String invideCode, String mobile) {
		return dao.updateInvideCode(invideCode, mobile);
	}

	@Override
	public List<User> getByparkid(String parkid) {
		return dao.getByparkid(parkid);
	}

	@Override
	public User selectBymobile(String mobile) {
		return dao.selectBymobile(mobile);
	}

	@Override
	public boolean updateis_guard(String invideCode, String is_guard) {
		return dao.updateis_guard(invideCode, is_guard);
	}

	@Override
	public boolean updateParkid(String is_admin,String invidecode) {
		return dao.updateParkid(is_admin,invidecode);
	}

	@Override
	public boolean updateis_admin(String invideCode) {
		return dao.updateis_admin(invideCode);
	}

	@Override
	public boolean updateadmin(String invideCode) {
		return dao.updateadmin(invideCode);
	}

	@Override
	public boolean updateparkid(String is_admin, String invideCode,
			String parkid) {
		return dao.updateparkid(is_admin, invideCode, parkid);
	}

	@Override
	public boolean updateuser(User user) {
		return dao.updateuser(user);
	}

	@Override
	public User getOne(String mobile) {
		return dao.getOne(mobile);
	}

	@Override
	public User getInfo(String mobile, String password) {
		return dao.getInfo(mobile,password);
	}

	@Override
	public User mobile(String mobile) {
		return dao.mobile(mobile);
	}

	@Override
	public boolean updateauth_code(String auth_code, String mobile) {
		return dao.updateauth_code(auth_code, mobile);
	}

	@Override
	public User mobileandpassword(String mobile) {
		return dao.mobileandpassword(mobile);
	}

	@Override
	public boolean updateregister(String password, String mobile) {
		return dao.updateregister(password, mobile);
	}

	@Override
	public boolean updateavatar(String avatar, String mobile) {
		return dao.updateavatar(avatar, mobile);
	}

	@Override
	public Pager<User> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex, pageSize);
	}

	@Override
	public boolean updateState(String id) {
		return dao.updateState(id);
	}

	@Override
	public boolean updatesex(String id) {
		return dao.updatesex(id);
	}

	@Override
	public boolean updateStates(String id) {
		return dao.updateStates(id);
	}

	@Override
	public List<Object> RegistCount( Integer year,Integer month){
		return dao.RegistCount(year, month);
	}

	@Override
	public String Bymobilenot(String mobile) {
		return dao.Bymobilenot(mobile);
	}

	@Override
	public String selectis_guardBymobile(String mobile) {
		return dao.selectis_guardBymobile(mobile);
	}

	@Override
	public String Selectmobile(String userid) {
		return dao.Selectmobile(userid);
	}

	@Override
	public boolean addVehicleLicense(VehicleLicense vehicle) {
		return dao.addVehicleLicense(vehicle);
	}

	@Override
	public boolean addinformation(information mation) {
		return dao.addinformation(mation);
	}

	@Override
	public boolean updateinformation(long duration,int jl,long times,String userid){
		return dao.updateinformation(duration, jl, times, userid);
	}

	@Override
	public String selectinformation(String userid) {
		return dao.selectinformation(userid);
	}

	@Override
	public information getinformation(String userid) {
		return dao.getinformation(userid);
	}

	@Override
	public boolean deleteinformation(String userid) {
		return dao.deleteinformation(userid);
	}
}
