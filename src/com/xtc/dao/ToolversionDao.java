package com.xtc.dao;

import java.util.List;

import com.xtc.entity.toolversion;

public interface ToolversionDao {
	/**
	 * ��ҵ�汾��ѯ����
	 * @param toonumber
	 * @return
	 */
	public List<toolversion> selectByToolversion();
}
