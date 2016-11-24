package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Park;
import com.xtc.util.Pager;

public interface IparkDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Park> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public Park selectOneBysyspaid(String userid);
	/**
	 * ��������
	 */
	public boolean insert(Park park);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updatepark(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name);
	public boolean updateparks(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name,String cost);

	public boolean updatecost(String cost,String name);
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deletepark(String id);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	public Park selectByparkid(String userid);
	/**
	 * ��������Ķ����ѯ
	 * @return
	 */
	public List<Park> selectByName(String name);
	/**
	 * ����parkid����ͣ������״̬
	 */
	public boolean updateState(String parkid,String state);
   /**
	*�鿴һ������
	*/
	public Park selectByOneName(String name);
	/**
	 * ����id�鿴һ������
	 */
	public Park selectByOneId(String id);
	/**
	 * ��ѯ���ƣ���ַ���շѣ�״̬
	 */
	public List<Object> selectObject();
	/**
	 * ����ͣ������userid,�ѹ�������
	 */
	public boolean  updateuserid(String parkname,String userid);
	
	public List<Object> selectPark(String userid);
	
	
	public List<Park> selectBystrs(String id);
	
	public String selectParkid(String userid);
	public String selectuserid(String name);
	/***
	 * �����ѯ
	 * @param map
	 * @return
	 */
	public List<Object> RegistCount(Integer year,Integer month);
	/**
	 * ���ݹ������ѯ�Ƿ��Ǳ������û�
	 */
	public String selectinvideCode(String relationCode);
	/**
	 * �����������ѯuserid
	 * @param invideCode
	 * @return
	 */
	public String selectbyuserid(String invideCode);
	/**
	 * ����userid��ѯ�������沢����10
	 * @param invideCode
	 * @return
	 */
	public String selectnotbalancedetail(String userid);
	public Pager<Park> selectAll(int pageIndex, int pageSize);
	public boolean updatemoralid(String moralid,String parkid);
	public String selectid(String name);
	public String selectmoralid(String name);
	public String selectgaodemoralid(String userid);
	public String selectprice(String parkid);
}
