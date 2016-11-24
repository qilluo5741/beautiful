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
	 * ��ҵ�˵�¼
	 * @param mobile
	 * @param password
	 * @return
	 */
	public sysuser getsysuserInfo(String mobile,String password);
	/**
	 * �޸�����
	 * @param first
	 * @param password
	 * @param mobile
	 * @return
	 */
	public boolean updatesysuserpwd(String first,String password,String mobile);
	/**
	 * ��ѯ��Ҫsysuser�Ĳ���
	 * @param mobile
	 * @return
	 */
	public sysuser selectsysuserfirst(String mobile);
	/**
	 * ��������ģ����ѯ
	 * @param name
	 * @return
	 */
	public List<Park> selectByParkName(String name);
	/**
	 * �����û�
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
	 * ��������
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
