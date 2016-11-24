package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Account;

public interface IAccountDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Account> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public Account selectOneByaccid(String accid);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Account account);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateAccount(Account account);
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteAccount(String accid);
	/**
	 * 根据userid查看一个对象
	 * @return
	 */
	public Account selectByUserid(String userid);
	/**
	 * 根据userid更新可用金额
	 */
	public boolean  updateByUserid(String userid,double balance);
}
