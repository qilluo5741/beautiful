package com.xtc.dao;

import java.util.List;
import com.xtc.entity.propertyation;

public interface propertyationDao {
	/**
	 * �����豸
	 * @param property
	 * @return
	 */
	public boolean addpropertyation(propertyation property);
	/**
	 * ��ѯ��ǰͣ�����ѹ����豸
	 * @param parkid
	 * @return
	 */
	public  List<propertyation> selectpropertyation(String parkid);
}
