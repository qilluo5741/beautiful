package com.xtc.service;

import java.util.List;

import com.xtc.entity.commodity;

public interface IcommodityService {
	public List<commodity>selectgetcommodityAll(String goodproperid);
	/**
	 * �޸Ŀ��
	 */
	public boolean updatecommodity(String commodityid,String commodicount);
}
