package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IAdvertisementDao;
import com.xtc.entity.Advertisement;
import com.xtc.service.IAdvertisementService;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class AdvertisementServiceImpl  implements IAdvertisementService{
	@Autowired
	private IAdvertisementDao dao;
	@Override
	public List<Advertisement> listALL() {
		return dao.listALL();
	}
	@Override
	public boolean insert(Advertisement advert) {
		return dao.insert(advert);
	}
}
