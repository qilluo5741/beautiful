package com.xtc.service;

import com.xtc.entity.drawcash;
import com.xtc.util.Pager;

public interface IdrawcashService {
	/**
	 * 	��ҳ��ѯ
	 */
	public Pager<drawcash> selectAll(int pageIndex, int pageSize);
	/**
	 * ����״̬
	 */
	public boolean updateState(String finaltime,String drawid);
}
