package com.xtc.dao;

import com.xtc.entity.drawcash;
import com.xtc.util.Pager;

public interface IdrawcashDao {
	/**
	 * 	��ҳ��ѯ
	 */
	public Pager<drawcash> selectAll(int pageIndex, int pageSize);

	/**
	 * ����״̬
	 */
	public boolean updateState(String finaltime,String drawid);

}
