package com.xtc.dao;

import java.util.List;
import com.xtc.entity.orderpark;

public interface IorderparkDao {
	public boolean insert(orderpark orderpark);
	public boolean updateorderpark(String evaluate,String pinId);
	public boolean updateorderparkr(String evaluatr,String pinId);
	public List<orderpark> selectBymobile(String usname);
}
