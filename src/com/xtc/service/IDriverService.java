package com.xtc.service;

import com.xtc.entity.Driver;

public interface IDriverService {
	// 根据手机号查询对象
	Driver getOne(String userid);

	// 更新
	boolean updateDriver(Driver d);

	// 添加
	boolean create(Driver d);
}
