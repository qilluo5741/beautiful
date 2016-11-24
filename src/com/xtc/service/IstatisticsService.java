package com.xtc.service;

import com.xtc.entity.statistics;

public interface IstatisticsService {
	public statistics selectBystatisticcount(String statisticid);
	public boolean  updateByselectBystatisticcount(int statisticcount,String statisticid);
}
