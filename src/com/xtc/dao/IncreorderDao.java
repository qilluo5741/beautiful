package com.xtc.dao;

import java.util.List;

import com.xtc.entity.increorder;

public interface IncreorderDao {
	public boolean Addincreorder(increorder increorder);
	public  List<Object> Increproperty(String userid,String repmenuid);
	public boolean updateIncreorder(String instatus,String intime,double inordermoney,String inorderNo);
	public List<increorder> selectUserid(String inorderNo);
}
