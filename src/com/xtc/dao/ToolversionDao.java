package com.xtc.dao;

import java.util.List;

import com.xtc.entity.toolversion;

public interface ToolversionDao {
	/**
	 * 物业版本查询更新
	 * @param toonumber
	 * @return
	 */
	public List<toolversion> selectByToolversion();
}
