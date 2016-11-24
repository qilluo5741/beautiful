package com.xtc.dao;

import java.util.List;

import com.xtc.entity.sysuser;
import com.xtc.entity.tollRecords;

public interface tollRecordsDao {
	/**
	 * 物业创建收费记录
	 * @param tollrecord
	 * @return
	 */
	public boolean addtollRecords(tollRecords tollrecord);
	/**
	 * 查看创建记录
	 * @param tollRphone
	 * @return
	 */
	public List<tollRecords> selecttollRecords(String tollRphone);
	/**
	 * 物业重置收费员密码
	 * @param password
	 * @param mobile
	 * @return
	 */
	public boolean updatetollRecords(String password,String mobile);
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public String selectBymobile(String mobile);
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public String selectBymobiles(String mobile);
	/**
	 * 修改
	 * @param name
	 * @param mobile
	 * @param password
	 * @param age
	 * @param is_owner
	 * @param is_admin
	 * @param first
	 * @param property
	 * @param isdelete
	 * @param parkid
	 * @return
	 */
	public boolean updateTollRecord(String name,String mobile,String password,String age,String is_owner,String is_admin,String first,String property,String isdelete,String parkid);
	public boolean delectTollRecord(String isdelete,String mobile);
	public sysuser selectByParkid(String mobile);
	public boolean deleteTollRecord(String tollRrecphone);
	public boolean deleteTollRecords(String mobile);
	public List<tollRecords> slectTollRecords(String parkid);
	public String selectBymtollRrecphone(String tollRrecphone);
}
