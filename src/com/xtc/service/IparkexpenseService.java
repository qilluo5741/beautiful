package com.xtc.service;

import java.util.List;

import com.xtc.entity.Orderinfo;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.parkexpense;
import com.xtc.entity.parkexpenses;
import com.xtc.entity.parking;
import com.xtc.entity.sysuser;
import com.xtc.util.Pager;

public interface IparkexpenseService {
	/**
	 * 
	 * @param expense
	 * @return
	 */
	boolean addparkexpense(parkexpense expense);
	/**
	 * 查询
	 * @param parkid
	 * @return
	 */
	public List<parkexpense> selectByexpense(String parkid,String userid,int pageIndex,int pageSize);
	public sysuser selectByuserid(String mobile);
	public boolean updateByparkexpense(String chargestatus,String userid,String parkdeid,String factorytime);
	/**
	 * 查询订单
	 */
	public Orderinfo selectByOrder(String order_num);
	public String selectBymobile(String userid);
	/**
	 * 查询车牌
	 * @param userid
	 * @return
	 */
	public List<VehicleLicense> selectByplate_no(String userid);
	/**
	 * 订单
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Object> selectByexpenseparkexpense(String sysuserid,Integer pageIndex,Integer pageSize);
	public boolean updateByparkuserid(String sysuserid,String licenseplate);
	public boolean updateBypaymentstatus(String paymentstatus,String ordernumber);
	public parkexpense getorderuserandparkid(String ordernumber);
	public Pager<parkexpenses> selectpgeryexpense(String mobile,Integer pageIndex,Integer pageSize);
	public Long selectByparkexpensescount(String mobile);
	boolean addparkexpenses(parkexpenses expense);
	public parking selectByparking(String parkid);
	public sysuser selectByparkmobileing(String userid);
	public parkexpense getorderuserandparkid(String plate_no,String datecreat);
}
