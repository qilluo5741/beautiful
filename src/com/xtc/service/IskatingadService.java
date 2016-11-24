package com.xtc.service;

import java.util.List;

import com.xtc.entity.skatingad;

public interface IskatingadService {
	/**
	 * （物业端）轮滑广告
	 * @return
	 */
	public List<skatingad> selectByskatingad();
}
