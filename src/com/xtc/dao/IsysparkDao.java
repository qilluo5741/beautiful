package com.xtc.dao;

import java.util.List;

import com.xtc.entity.parking;
import com.xtc.entity.syspark;
import com.xtc.entity.dto.parkinfoDto;

public interface IsysparkDao {
	/**
	 * 搜索停车场名字
	 * @param name
	 * @return
	 */
	public List<parkinfoDto> selectByNameorAddress(String name);
	/**
	 * 查询停车场
	 * @param parkid
	 * @return
	 */
	public List<syspark> getParkcreat(String parkid);
	/**
	 * 停车场修改
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
	 * tcc添加
	 * @param park
	 * @return
	 */
	public boolean addsyspark(parking park);
}
