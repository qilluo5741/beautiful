package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Park;
import com.xtc.entity.sysuser;
/**
 * 
 * @author weimei
 *
 */
public interface SysuserDao {
	/**
	 * 物业端登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	public sysuser getsysuserInfo(String mobile,String password);
	/**
	 * 修改密码
	 * @param first
	 * @param password
	 * @param mobile
	 * @return
	 */
	public boolean updatesysuserpwd(String first,String password,String mobile);
	/**
	 * 查询需要sysuser的参数
	 * @param mobile
	 * @return
	 */
	public sysuser selectsysuserfirst(String mobile);
	/**
	 * 工具名字模糊查询
	 * @param name
	 * @return
	 */
	public List<Park> selectByParkName(String name);
	/**
	 * 创建用户
	 * @param sysuser
	 * @return
	 */
	public boolean addsysuser(sysuser sysuser);
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public sysuser sysusermobile(String mobile);
	/**
	 * 
	 */
	public String selectmobile(String mobile);
	/**
	 * 关联保安
	 * @param parkid
	 * @param name
	 * @param mobile
	 * @param is_admin
	 * @return
	 */
	public boolean updatesysuserrelation(String parkid,String isdelete,String property,String name,String mobile,String is_admin);
	public boolean updateparkuser(String userid,String parkid);
	public List<Object> selectusermobile(String parkid);
	public boolean updatesysuserrelation(String is_admin,String parkId,String mobile);
	public boolean updatesysuserrelations(String is_admin,String parkId,String isdelete,String mobile);
	public List<sysuser> selectsysusermobilet(String parkid);
}
