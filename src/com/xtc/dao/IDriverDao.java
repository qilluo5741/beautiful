package com.xtc.dao;

import com.xtc.entity.Driver;


public interface IDriverDao {
	//�����ֻ��Ų�ѯ����
	Driver getOne(String userid);
	//����
	boolean updateDriver(Driver d);
	//���
	boolean create(Driver d);
}
