package com.xtc.service;

import java.util.List;

import com.xtc.entity.propertyation;

public interface propertyationService {
	/**
	 * 关联设备
	 * @param property
	 * @return
	 */
	public boolean addpropertyation(propertyation property);
	/**
	 * 查询当前停车场已关联设备
	 * @param parkid
	 * @return
	 */
	public  List<propertyation> selectpropertyation(String parkid);
}
