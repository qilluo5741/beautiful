package com.xtc.dao;

import java.util.List;

import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.information;
import com.xtc.util.Pager;

public interface IUserDao {
	
	public User getInfo(String mobile,String password);
	/**
	 * 查询所有
	 * @return
	 */
	public List<User> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public User selectOneByuid(String uid);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(User user);
	/**
	 * 根据手机号修改用户的状态
	 * @return
	 */
	public boolean updateUser(String mobile,String state);
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteUser(String uid);
	/**
	 * 根据userid查看一个对象
	 * @return
	 */
	public List<User> selectByUserid(String userid);
	/**
	 * 根据手机号查看一个对象
	 */
	public List<User> selectBymobil(String mobil);
	/**
	 * 根据邀请码查询一个对象
	 */
	public User selectOne(String invideCode);
	/**
	 * 根据手机号码更新邀请码
	 */
	public boolean updateInvideCode(String invideCode,String mobile);
	/**
	 * 根据停车场的id查询用户
	 */
	public List<User> getByparkid(String parkid);
	
	public User selectBymobile(String mobile);
	/**
	 * 更新用户表
	 */
	public boolean updateis_guard(String invideCode,String is_guard);
	/**
	 * 更新用户表的parkid
	 */
	public boolean updateParkid(String is_admin,String invidecode);
	/**
	 * 填充用户表的parkid
	 */
	public boolean updateparkid(String is_admin,String invideCode,String parkid);
	public boolean updateis_admin(String invideCode);
	public boolean updateadmin(String invideCode);
	/**
	 * 更新一个对象
	 * @param user
	 * @return
	 */
	public boolean updateuser(User user);
	//根据手机号查看一个对象
	public User getOne(String mobile);
	
	public User mobile(String mobile);
	public String Bymobilenot(String mobile);
	public boolean updateauth_code(String auth_code,String mobile);
	public User mobileandpassword(String mobile);
	public boolean updateregister(String password,String mobile);
	//修改头像
	public boolean updateavatar(String avatar,String mobile);
	public Pager<User> selectAll(int pageIndex, int pageSize);
	public boolean updateState(String id);
	public boolean updateStates(String id);
	public boolean updatesex(String id);
	/***
	 * 报表查询
	 * @param map
	 * @return
	 */
	public List<Object> RegistCount(Integer year,Integer month);
	public String selectis_guardBymobile(String mobile);
	public String Selectmobile(String userid);
	public boolean addVehicleLicense(VehicleLicense vehicle);
	public boolean addinformation(information mation);
	public boolean updateinformation(long duration,int jl,long times,String userid);
	public String selectinformation(String userid);
	public information getinformation(String userid);
	public boolean deleteinformation(String userid);
}
