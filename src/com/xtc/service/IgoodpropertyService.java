package com.xtc.service;

import java.util.List;

import com.xtc.entity.goodproperty;

public interface IgoodpropertyService {
	/**
	 * 查询所有商品
	 * @param repmenuid
	 * @return
	 */
	public List<goodproperty>selectgetgoodpropertyAll(String repmenuid);
}
