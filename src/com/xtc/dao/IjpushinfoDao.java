package com.xtc.dao;
import java.util.List;

import com.xtc.entity.jpushinfo;

public interface IjpushinfoDao{
	public List<jpushinfo> selectByUserid(String userid);
	public boolean insert(jpushinfo jpush);
	public boolean updatejpushinfo(String regid,String userid);
	public String selectByregid(String userid);
}
