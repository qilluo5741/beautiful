package com.xtc.dao;

import com.xtc.entity.Driver;


public interface IDriverDao {
	//根据手机号查询对象
	Driver getOne(String userid);
	//更新
	boolean updateDriver(Driver d);
	//添加
	boolean create(Driver d);
}
