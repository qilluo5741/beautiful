package com.xtc.dao;

import com.xtc.entity.Userinfo;

public interface IUserinfoDao {
	//��¼
	public Userinfo login(String userName,String userPwd);
}
