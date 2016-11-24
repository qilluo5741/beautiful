package com.xtc.service;

import java.util.List;

import com.xtc.entity.DrawcashInfo;

public interface IDrawcashInfoService {
	/**
	 * 查询所有
	 * @return
	 */
	public List<DrawcashInfo> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public DrawcashInfo selectOneBydid(String did);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(DrawcashInfo drawcashInfo);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateDrawcashInfo();
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteDrawcashInfo(String userid);
	/**
	 * 根据bankid查看一个对象
	 * @return
	 */
	public DrawcashInfo selectBybankid(String bankid);
	/**
	 * 根据userid查看一个对象
	 * @param userid
	 * @return
	 */
	public List<DrawcashInfo> selectByUserid(String userid);
	/**
	 * 余额提现记录
	 * @param drawid
	 * @return
	 */
	public DrawcashInfo selectBydrawid(String drawid);
	/**
	 *查询提现记录表，用于支付宝的支付审核
	 */
	//public List<Object> selectObject();
}
