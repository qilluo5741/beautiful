package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IparkexpenseDao;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.parkexpense;
import com.xtc.entity.parkexpenses;
import com.xtc.entity.parking;
import com.xtc.entity.sysuser;
import com.xtc.service.IparkexpenseService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class IparkexpenseServiceImpl implements IparkexpenseService {
	@Autowired
	private IparkexpenseDao dao;
	@Override
	public boolean addparkexpense(parkexpense expense) {
		return dao.addparkexpense(expense);
	}

	@Override
	public List<parkexpense> selectByexpense(String parkid, String userid, int pageIndex, int pageSize) {
		return dao.selectByexpense(parkid, userid, pageIndex, pageSize);
	}

	@Override
	public sysuser selectByuserid(String mobile) {
		return dao.selectByuserid(mobile);
	}

	@Override
	public boolean updateByparkexpense(String chargestatus, String userid, String parkdeid, String factorytime) {
		return dao.updateByparkexpense(chargestatus, userid, parkdeid, factorytime);
	}

	@Override
	public Orderinfo selectByOrder(String order_num) {
		return dao.selectByOrder(order_num);
	}

	@Override
	public String selectBymobile(String userid) {
		return dao.selectBymobile(userid);
	}

	@Override
	public List<VehicleLicense> selectByplate_no(String userid) {
		return dao.selectByplate_no(userid);
	}

	@Override
	public List<Object> selectByexpenseparkexpense(String sysuserid, Integer pageIndex, Integer pageSize) {
		return dao.selectByexpenseparkexpense(sysuserid, pageIndex, pageSize);
	}

	@Override
	public boolean updateByparkuserid(String sysuserid, String licenseplate) {
		return dao.updateByparkuserid(sysuserid, licenseplate);
	}

	@Override
	public boolean updateBypaymentstatus(String paymentstatus, String ordernumber) {
		return dao.updateBypaymentstatus(paymentstatus, ordernumber);
	}

	@Override
	public parkexpense getorderuserandparkid(String ordernumber) {
		return dao.getorderuserandparkid(ordernumber);
	}
	@Override
	public Pager<parkexpenses> selectpgeryexpense(String mobile, Integer pageIndex, Integer pageSize) {
		return dao.selectpgeryexpense(mobile, pageIndex, pageSize);
	}

	@Override
	public Long selectByparkexpensescount(String mobile) {
		return dao.selectByparkexpensescount(mobile);
	}

	@Override
	public boolean addparkexpenses(parkexpenses expense) {
		return dao.addparkexpenses(expense);
	}

	@Override
	public parking selectByparking(String parkid) {
		return dao.selectByparking(parkid);
	}

	@Override
	public sysuser selectByparkmobileing(String userid) {
		return dao.selectByparkmobileing(userid);
	}

	@Override
	public parkexpense getorderuserandparkid(String plate_no, String datecreat) {
		return dao.getorderuserandparkid(plate_no, datecreat);
	}
}
