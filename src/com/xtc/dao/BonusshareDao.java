package com.xtc.dao;

import com.xtc.entity.bonusshare;

public interface BonusshareDao {
	//���
	public boolean insert(bonusshare b);
	//����bonusmobile�鿴
	public bonusshare selectByMobile(String mobile,String order_num);
	public int selectbonuscount(String mobile,String bonusdate);
	//�޸�
	public boolean updateCount(int bonuscount,String mobile,String bonusdate);
}
