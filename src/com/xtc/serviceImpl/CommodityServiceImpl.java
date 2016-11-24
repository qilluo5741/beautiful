package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IcommodityDao;
import com.xtc.entity.commodity;
import com.xtc.service.IcommodityService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class CommodityServiceImpl implements IcommodityService {
	@Autowired
	private IcommodityDao dao;
	@Override
	public List<commodity> selectgetcommodityAll(String goodproperid) {
		return dao.selectgetcommodityAll(goodproperid);
	}
	@Override
	public boolean updatecommodity(String commodityid, String commodicount) {
		return dao.updatecommodity(commodityid, commodicount);
	}

}
