package com.xtc.service;

import com.xtc.entity.Driver;

public interface IDriverService {
	// �����ֻ��Ų�ѯ����
	Driver getOne(String userid);

	// ����
	boolean updateDriver(Driver d);

	// ���
	boolean create(Driver d);
}
