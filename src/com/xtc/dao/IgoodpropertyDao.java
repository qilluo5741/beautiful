package com.xtc.dao;

import java.util.List;

import com.xtc.entity.goodproperty;

public interface IgoodpropertyDao {
	/**
	 * ��ѯ������Ʒ
	 * @param repmenuid
	 * @return
	 */
	public List<goodproperty>selectgetgoodpropertyAll(String repmenuid);
}
