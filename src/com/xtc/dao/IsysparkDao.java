package com.xtc.dao;

import java.util.List;

import com.xtc.entity.parking;
import com.xtc.entity.syspark;
import com.xtc.entity.dto.parkinfoDto;

public interface IsysparkDao {
	/**
	 * ����ͣ��������
	 * @param name
	 * @return
	 */
	public List<parkinfoDto> selectByNameorAddress(String name);
	/**
	 * ��ѯͣ����
	 * @param parkid
	 * @return
	 */
	public List<syspark> getParkcreat(String parkid);
	/**
	 * ͣ�����޸�
	 * @param city
	 * @param entry_address
	 * @param address
	 * @param capacity
	 * @param carnum
	 * @param type
	 * @param price
	 * @param is_cooperate
	 * @param name
	 * @param cost
	 * @param reservation
	 * @param dividedinto
	 * @return
	 */
	public boolean updatesyspark(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name,String cost,String reservation,String dividedinto,double subscription,String parkid);
	/**
	 * tcc���
	 * @param park
	 * @return
	 */
	public boolean addsyspark(parking park);
}
