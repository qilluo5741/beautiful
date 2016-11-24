package com.xtc.service;

import java.util.List;

import com.xtc.entity.Park;
import com.xtc.util.Pager;

public interface IparkService {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Park> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public Park selectOneBysyspaid(String id);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Park park);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updatepark(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name);
	public boolean updateparks(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name,String cost);
	public boolean updatecost(String cost,String name);
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deletepark(String id);
	/**
	 * 根据userid查看一个对象
	 * @return
	 */
	public Park selectByparkid(String userid);
	/**
	 * 根据输入的对象查询
	 * @return
	 */
	public List<Park> selectByName(String name);
	
	public boolean updateState(String parkid, String state);
	
	public Park selectByOneName(String name);
	public List<Object> selectObject() ;
	public Park selectByOneId(String id) ;
	public boolean updateuserid(String parkname, String userid);
	public List<Object> selectPark(String userid);
	public List<Park> selectBystrs(String id);
	public String selectParkid(String userid);
	public String selectuserid(String name);
	/***
	 * 报表查询
	 * @param map
	 * @return
	 */
	public List<Object> RegistCount(Integer year,Integer month);
	/**
	 * 根据关联码查询是否是被邀请用户
	 */
	public String selectinvideCode(String relationCode);
	/**
	 * 根据邀请码查询userid
	 * @param invideCode
	 * @return
	 */
	public String selectbyuserid(String invideCode);
	public String selectnotbalancedetail(String userid);
	public Pager<Park> selectAll(int pageIndex, int pageSize);
	public boolean updatemoralid(String moralid,String parkid);
	public String selectid(String name);
	public String selectmoralid(String name);
	public String selectgaodemoralid(String userid);
	public String selectprice(String parkid);
}
