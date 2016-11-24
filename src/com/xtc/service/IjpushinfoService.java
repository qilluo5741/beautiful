package com.xtc.service;
import java.util.List;

import com.xtc.entity.jpushinfo;

public interface IjpushinfoService{
	public  List<jpushinfo> selectByUserid(String userid);
	public boolean insert(jpushinfo jpush);
	public boolean updatejpushinfo(String regid,String userid);
	public String selectByregid(String userid);
}
