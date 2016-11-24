package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Stateinfo;

public interface IStateinfoDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Stateinfo> selectAll(String parkid);
	/**
	 * 单个查询
	 * @return
	 */
	public Stateinfo selectOneBysid(String sid);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Stateinfo stateinfo);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateStateinfo();
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteStateinfo(String userid);
	
}
