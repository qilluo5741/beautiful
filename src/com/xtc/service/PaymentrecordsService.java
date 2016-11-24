package com.xtc.service;

import java.util.List;

import com.xtc.entity.paymentrecords;

public interface PaymentrecordsService {
	public boolean addPaymentrecord(paymentrecords payment);
	public List<paymentrecords> selectBypaymentrecords(String userid,Integer pageIndex,Integer pageSize);
}
