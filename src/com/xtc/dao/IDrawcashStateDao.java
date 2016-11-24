package com.xtc.dao;

import java.util.List;

import com.xtc.entity.DrawcashState;

public interface IDrawcashStateDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<DrawcashState> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public DrawcashState selectOneBybid(String accid);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(DrawcashState DrawcashState);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateDrawcashState( );
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteDrawcashState(String userid);
	/**
	 * 根据提现记录的id查看状态记录表
	 * @return
	 */
	public DrawcashState selectBydid(String did);
}
