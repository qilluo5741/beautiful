package com.xtc.dao;

import com.xtc.entity.statistics;

public interface IstatisticsDao {
	public statistics selectBystatisticcount(String statisticid);
	public boolean  updateByselectBystatisticcount(int statisticcount,String statisticid);
}
