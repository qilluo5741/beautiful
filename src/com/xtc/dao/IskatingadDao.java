package com.xtc.dao;

import java.util.List;

import com.xtc.entity.skatingad;

public interface IskatingadDao {
	/**
	 * （物业端）轮滑广告
	 * @return
	 */
	public List<skatingad> selectByskatingad();
}
