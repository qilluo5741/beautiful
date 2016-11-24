package com.xtc.service;


import java.util.List;

import com.xtc.entity.VehicleLicense;

public interface IvehicleLicenseService {
	public VehicleLicense selectByplateNo(String plateNo);
	//根据用户id查询车牌号的集合
		public List<VehicleLicense> getByUserid(String userid);
		//删除
		public boolean delete(String userid) ;
		//根据userid和车牌号查询对象
		public VehicleLicense getVL(String userid,String plate_no);
		//添加
		public boolean create(VehicleLicense vl);
		//更新
		public boolean update(VehicleLicense vl);
}
