package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IsysparkDao;
import com.xtc.entity.parking;
import com.xtc.entity.syspark;
import com.xtc.entity.dto.parkinfoDto;
import com.xtc.service.IsysparkService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class IsysparkServiceImpl implements IsysparkService {
	@Autowired
	private IsysparkDao dao;
	@Override
	public List<parkinfoDto> selectByNameorAddress(String name) {
		return dao.selectByNameorAddress(name);
	}
	@Override
	public List<syspark> getParkcreat(String parkid) {
		return dao.getParkcreat(parkid);
	}
	@Override
	public boolean updatesyspark(String city, String entry_address, String address, String capacity, String carnum,String type, String price, String is_cooperate, String name, String cost, String reservation,String dividedinto,double subscription,String parkid) {
		return dao.updatesyspark(city, entry_address, address, capacity, carnum, type, price, is_cooperate, name, cost, reservation, dividedinto,subscription,parkid);
	}
	@Override
	public boolean addsyspark(parking park) {
		return dao.addsyspark(park);
	}
}
