package com.xtc.dao;

import java.util.List;

import com.xtc.entity.relation;
import com.xtc.util.Pager;


public interface IrelationDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<relation> selectAll();
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(relation relation);
	/**
	 * 
	 * 根据invideCode查询
	 * @param invideCode
	 * @return
	 */
	public relation selectinvideCode(String invideCode);
	/**
	 * 	分页查询
	 */
	public Pager<relation> selectAll(int pageIndex, int pageSize);
}
