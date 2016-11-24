package com.xtc.dao;

import java.util.List;

import com.xtc.entity.goodproperty;

public interface IgoodpropertyDao {
	/**
	 * 查询所有商品
	 * @param repmenuid
	 * @return
	 */
	public List<goodproperty>selectgetgoodpropertyAll(String repmenuid);
}
