package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.PaymentrecordsDao;
import com.xtc.entity.paymentrecords;
import com.xtc.service.PaymentrecordsService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class PaymentrecordsServiceImpl implements PaymentrecordsService {
	@Autowired
	private PaymentrecordsDao dao;
	@Override
	public boolean addPaymentrecord(paymentrecords payment) {
		return dao.addPaymentrecord(payment);
	}
	@Override
	public List<paymentrecords> selectBypaymentrecords(String userid, Integer pageIndex, Integer pageSize) {
		return dao.selectBypaymentrecords(userid, pageIndex, pageSize);
	}
}
