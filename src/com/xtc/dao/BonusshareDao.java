package com.xtc.dao;

import com.xtc.entity.bonusshare;

public interface BonusshareDao {
	//添加
	public boolean insert(bonusshare b);
	//根据bonusmobile查看
	public bonusshare selectByMobile(String mobile,String order_num);
	public int selectbonuscount(String mobile,String bonusdate);
	//修改
	public boolean updateCount(int bonuscount,String mobile,String bonusdate);
}
