package com.xtc.dao;

import java.util.List;

import com.xtc.entity.balancedetail;
import com.xtc.util.Pager;

public interface IbalancedetailDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public Pager<balancedetail> selectAll(int pageIndex, int pageSize);
	/**
	 * ��������
	 */
	public boolean insert(balancedetail balan);
	/**
	 * ��������Ķ����ѯ
	 * @return
	 */
	public List<balancedetail> selectByuserid(String userid,int pageIndex,int pageSize);
	
	public String selectBybalancetype(String userid);
	
	public boolean updateBybalancetype(String balancetype,String userid);
}
