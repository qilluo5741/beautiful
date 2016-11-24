package com.xtc.dao;

import java.util.List;

import com.xtc.entity.VehicleLicense;

public interface IvehicleLicenseDao {
	public VehicleLicense selectByplateNo(String plateNo);
	//�����û�id��ѯ���ƺŵļ���
	public List<VehicleLicense> getByUserid(String userid);
	//ɾ��
	public boolean delete(String userid);
	//����userid�ͳ��ƺŲ�ѯ����
	public VehicleLicense getVL(String userid,String plate_no);
	//���
	public boolean create(VehicleLicense vl);
	//����
	public boolean update(VehicleLicense vl);
	
}
