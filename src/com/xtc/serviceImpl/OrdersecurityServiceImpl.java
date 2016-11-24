package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.OrdersecurityDao;
import com.xtc.entity.Ordersecurity;
import com.xtc.service.OrdersecurityService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OrdersecurityServiceImpl implements OrdersecurityService {
	@Autowired
	private OrdersecurityDao dao;
	@Override
	public boolean addOrdersecurity(Ordersecurity order) {
		return dao.addOrdersecurity(order);
	}
}
