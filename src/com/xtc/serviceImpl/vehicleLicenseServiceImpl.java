package com.xtc.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IvehicleLicenseDao;
import com.xtc.entity.VehicleLicense;
import com.xtc.service.IvehicleLicenseService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class vehicleLicenseServiceImpl implements IvehicleLicenseService {
	@Autowired
	private IvehicleLicenseDao dao;

	@Override
	public VehicleLicense selectByplateNo(String plateNo) {
		return dao.selectByplateNo(plateNo);
	}
	/*@Override
	public List<VehicleLicense> selectByplateNo(String plateNo) {
		return dao.selectByplateNo(plateNo);
	}*/

	@Override
	public List<VehicleLicense> getByUserid(String userid) {
		return dao.getByUserid(userid);
	}

	@Override
	public boolean delete(String userid) {
		return dao.delete(userid);
	}

	@Override
	public VehicleLicense getVL(String userid, String plate_no) {
		return dao.getVL(userid, plate_no);
	}

	@Override
	public boolean create(VehicleLicense vl) {
		return dao.create(vl);
	}

	@Override
	public boolean update(VehicleLicense vl) {
		return dao.update(vl);
	}
	
}
