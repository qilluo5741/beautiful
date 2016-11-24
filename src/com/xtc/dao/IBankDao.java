package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Bank;

public interface IBankDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Bank> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public Bank selectOneBybid(String bid);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Bank bank);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateBank( String accountname , String accountnumbe,String bankname ,
	    String bankplace , String subbank ,String userid );
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteBank(String userid);
	/**
	 * 根据userid查看一个对象
	 * @return
	 */
	public Bank selectByUserid(String userid);
	/**
	 * 查询所有的userid
	 */
	public List<String> getuserids();
}
