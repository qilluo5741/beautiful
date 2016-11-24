package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IDiscountDao;
import com.xtc.entity.Discount;
import com.xtc.service.IDiscountService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class DiscountServiceImpl implements IDiscountService{
@Autowired
private IDiscountDao dao;
	@Override
	public List<Discount> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Discount selectOneBydid(String did) {
		return dao.selectOneBydid(did);
	}

	@Override
	public boolean insert(Discount discount) {
		return dao.insert(discount);
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		return dao.updateDiscount(discount);
	}

	@Override
	public boolean deleteDiscount(String did) {
		return dao.deleteDiscount(did);
	}
}
