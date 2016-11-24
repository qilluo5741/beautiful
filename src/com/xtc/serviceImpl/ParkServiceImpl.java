package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IparkDao;
import com.xtc.entity.Park;
import com.xtc.service.IparkService;
import com.xtc.util.Pager;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ParkServiceImpl implements IparkService {
	@Autowired
	private IparkDao dao;
	@Override
	public List<Park> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Park selectOneBysyspaid(String userid) {
		return dao.selectOneBysyspaid(userid);
	}

	@Override
	public boolean insert(Park park) {
		return dao.insert(park);
	}

	@Override
	public boolean updatepark(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name) {
		return dao.updatepark(city,entry_address,address,capacity,carnum,type,price,is_cooperate,name);
	}
	@Override
	public boolean updateparks(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name,String cost) {
		return dao.updateparks(city,entry_address,address,capacity,carnum,type,price,is_cooperate,name,cost);
	}
	@Override
	public boolean deletepark(String id) {
		return dao.deletepark(id);
	}

	@Override
	public Park selectByparkid(String userid) {
		return dao.selectByparkid(userid);
	}

	@Override
	public List<Park> selectByName(String name){
		return dao.selectByName(name);
	}
	@Override
	public boolean updateState(String parkid, String state) {
		return dao.updateState(parkid, state);
	}

	@Override
	public Park selectByOneName(String name) {
		return dao.selectByOneName(name);
	}

	@Override
	public List<Object> selectObject() {
		return dao.selectObject();
	}

	@Override
	public Park selectByOneId(String id) {
		return dao.selectByOneId(id);
	}

	@Override
	public boolean updateuserid(String parkname, String userid) {
		return dao.updateuserid(parkname, userid);
	}

	@Override
	public List<Object> selectPark(String userid) {
		return dao.selectPark(userid);
	}

	@Override
	public List<Park> selectBystrs(String id) {
		return dao.selectBystrs(id);
	}

	@Override
	public String selectParkid(String userid) {
		return dao.selectParkid(userid);
	}

	@Override
	public String selectuserid(String name) {
		return dao.selectuserid(name);
	}

	@Override
	public boolean updatecost(String cost, String name) {
		return dao.updatecost(cost, name);
	}

	@Override
	public List<Object> RegistCount(Integer year, Integer month) {
		return dao.RegistCount(year, month);
	}
	/**
	 * 根据关联码查询是否是被邀请用户
	 */
	@Override
	public String selectinvideCode(String relationCode) {
		return dao.selectinvideCode(relationCode);
	}
	/**
	 * 根据邀请码查询userid
	 * @param invideCode
	 * @return
	 */
	@Override
	public String selectbyuserid(String invideCode) {
		return dao.selectbyuserid(invideCode);
	}

	@Override
	public String selectnotbalancedetail(String userid) {
		return dao.selectnotbalancedetail(userid);
	}

	@Override
	public Pager<Park> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex, pageSize);
	}

	@Override
	public boolean updatemoralid(String moralid, String parkid) {
		return dao.updatemoralid(moralid, parkid);
	}

	@Override
	public String selectid(String name) {
		return dao.selectid(name);
	}

	@Override
	public String selectmoralid(String name) {
		return dao.selectmoralid(name);
	}

	@Override
	public String selectgaodemoralid(String userid) {
		return dao.selectgaodemoralid(userid);
	}

	@Override
	public String selectprice(String parkid) {
		return dao.selectprice(parkid);
	}
}
