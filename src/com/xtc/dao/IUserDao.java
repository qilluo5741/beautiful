package com.xtc.dao;

import java.util.List;

import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.information;
import com.xtc.util.Pager;

public interface IUserDao {
	
	public User getInfo(String mobile,String password);
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<User> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public User selectOneByuid(String uid);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(User user);
	/**
	 * �����ֻ����޸��û���״̬
	 * @return
	 */
	public boolean updateUser(String mobile,String state);
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteUser(String uid);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	public List<User> selectByUserid(String userid);
	/**
	 * �����ֻ��Ų鿴һ������
	 */
	public List<User> selectBymobil(String mobil);
	/**
	 * �����������ѯһ������
	 */
	public User selectOne(String invideCode);
	/**
	 * �����ֻ��������������
	 */
	public boolean updateInvideCode(String invideCode,String mobile);
	/**
	 * ����ͣ������id��ѯ�û�
	 */
	public List<User> getByparkid(String parkid);
	
	public User selectBymobile(String mobile);
	/**
	 * �����û���
	 */
	public boolean updateis_guard(String invideCode,String is_guard);
	/**
	 * �����û����parkid
	 */
	public boolean updateParkid(String is_admin,String invidecode);
	/**
	 * ����û����parkid
	 */
	public boolean updateparkid(String is_admin,String invideCode,String parkid);
	public boolean updateis_admin(String invideCode);
	public boolean updateadmin(String invideCode);
	/**
	 * ����һ������
	 * @param user
	 * @return
	 */
	public boolean updateuser(User user);
	//�����ֻ��Ų鿴һ������
	public User getOne(String mobile);
	
	public User mobile(String mobile);
	public String Bymobilenot(String mobile);
	public boolean updateauth_code(String auth_code,String mobile);
	public User mobileandpassword(String mobile);
	public boolean updateregister(String password,String mobile);
	//�޸�ͷ��
	public boolean updateavatar(String avatar,String mobile);
	public Pager<User> selectAll(int pageIndex, int pageSize);
	public boolean updateState(String id);
	public boolean updateStates(String id);
	public boolean updatesex(String id);
	/***
	 * �����ѯ
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
