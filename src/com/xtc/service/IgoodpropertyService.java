package com.xtc.service;

import java.util.List;

import com.xtc.entity.goodproperty;

public interface IgoodpropertyService {
	/**
	 * ��ѯ������Ʒ
	 * @param repmenuid
	 * @return
	 */
	public List<goodproperty>selectgetgoodpropertyAll(String repmenuid);
}
