package com.xtc.dao;

import java.util.List;

import com.xtc.entity.commodity;

public interface IcommodityDao {
	public List<commodity>selectgetcommodityAll(String goodproperid);
	/**
	 * �޸Ŀ��
	 */
	public boolean updatecommodity(String commodityid,String commodicount);
}
