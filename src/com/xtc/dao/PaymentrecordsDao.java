package com.xtc.dao;

import java.util.List;

import com.xtc.entity.paymentrecords;

public interface PaymentrecordsDao {
	public boolean addPaymentrecord(paymentrecords payment);
	public List<paymentrecords> selectBypaymentrecords(String userid,Integer pageIndex,Integer pageSize);
}
