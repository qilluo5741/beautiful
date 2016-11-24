package com.xtc.dao;

import java.util.List;

import com.xtc.entity.redshare;

public interface IredshareDao {
	/**
	 * ≤Â»Î ˝æ›
	 * @return
	 */
	public boolean insert(redshare redshare);
	public List<redshare> selectredshare(String sharemobile,String maxdate);
	public List<Object> selectredshares(String sharemobile,String maxdate);
	public List<redshare> selectshareid(String shareid);
	public boolean updateOrdersharemoney(double sharemoney,String order_num);
	public boolean updatesharedel(String shareid);
	public List<redshare> selectredmobilee(String mobile);
	public String selectbonuscount(String sharedate,String mobile,String sharemobile);
	public boolean deletesharemobile(String sharedate,String mobile);
	public redshare selectmoney(String mobile,String sharedate);
	public String selectOneByid(String order_num);
}
