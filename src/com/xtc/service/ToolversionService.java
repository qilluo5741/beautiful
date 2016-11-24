package com.xtc.service;

import java.util.List;

import com.xtc.entity.toolversion;

public interface ToolversionService {
	/**
	 * 物业版本查询更新
	 * @param toonumber
	 * @return
	 */
	public List<toolversion> selectByToolversion();
}
