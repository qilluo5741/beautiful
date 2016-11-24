package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IstatisticsDao;
import com.xtc.entity.statistics;
import com.xtc.service.IstatisticsService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class statisticsServiceimpl implements IstatisticsService {
	@Autowired
	private IstatisticsDao dao;
	@Override
	public statistics selectBystatisticcount(String statisticid) {
		return dao.selectBystatisticcount(statisticid);
	}

	@Override
	public boolean updateByselectBystatisticcount(int statisticcount, String statisticid) {
		return dao.updateByselectBystatisticcount(statisticcount, statisticid);
	}

}
