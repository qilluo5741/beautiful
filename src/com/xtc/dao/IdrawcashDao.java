package com.xtc.dao;

import com.xtc.entity.drawcash;
import com.xtc.util.Pager;

public interface IdrawcashDao {
	/**
	 * 	分页查询
	 */
	public Pager<drawcash> selectAll(int pageIndex, int pageSize);

	/**
	 * 更新状态
	 */
	public boolean updateState(String finaltime,String drawid);

}
