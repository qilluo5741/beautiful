package com.xtc.service;

import java.util.List;

import com.xtc.entity.toolversion;

public interface ToolversionService {
	/**
	 * ��ҵ�汾��ѯ����
	 * @param toonumber
	 * @return
	 */
	public List<toolversion> selectByToolversion();
}
