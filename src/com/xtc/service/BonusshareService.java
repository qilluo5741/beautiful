package com.xtc.service;

import com.xtc.entity.bonusshare;

public interface BonusshareService {
	//���
	public boolean insert(bonusshare b);
	//����bonusmobile�鿴
	public bonusshare selectByMobile(String mobile,String order_num);
	public boolean updateCount(int bonuscount,String mobile,String bonusdate);
	public int selectbonuscount(String mobile,String bonusdate);
}

