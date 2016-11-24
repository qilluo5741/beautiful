package com.xtc.service;

import java.util.List;

import com.xtc.entity.balancedetail;
import com.xtc.util.Pager;

public interface IbalancedetailService {
	/**
	 * 查询所有
	 * @return
	 */
	public  Pager<balancedetail> selectAll(int pageIndex, int pageSize);
	/**
	 * 插入数据
	 */
	public boolean insert(balancedetail balan);
	/**
	 * 根据输入的对象查询
	 * @return
	 */
	public List<balancedetail> selectByuserid(String userid,int pageIndex,int pageSize);
	
	public String selectBybalancetype(String userid);
	public boolean updateBybalancetype(String balancetype,String userid);
}
