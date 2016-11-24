package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.GoodrepairDao;
import com.xtc.entity.Goodrepair;
import com.xtc.service.IGoodrepairService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class GoodrepairServiceImpl implements IGoodrepairService {
	@Autowired
	private GoodrepairDao dao;
	@Override
	public List<Goodrepair> selectGoodrepairAll(String goodproperid){
		return dao.selectGoodrepairAll(goodproperid);
	}
}
