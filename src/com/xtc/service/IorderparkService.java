package com.xtc.service;

import java.util.List;

import com.xtc.entity.orderpark;

public interface IorderparkService {
	public boolean insert(orderpark orderpark);
	public boolean updateorderpark(String evaluate,String pinId);
	public boolean updateorderparkr(String evaluatr,String pinId);
	public List<orderpark> selectBymobile(String usname);
}
