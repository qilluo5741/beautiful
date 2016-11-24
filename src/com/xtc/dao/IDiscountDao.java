package com.xtc.dao;

import java.util.List;
import com.xtc.entity.Discount;

public interface IDiscountDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Discount> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public Discount selectOneBydid(String did);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Discount discount);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateDiscount(Discount discount);
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteDiscount(String did);
	
}
