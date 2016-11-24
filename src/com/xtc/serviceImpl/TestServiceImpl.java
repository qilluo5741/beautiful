package com.xtc.serviceImpl;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.ITestDao;
import com.xtc.entity.TestInfo;
import com.xtc.service.ITestService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TestServiceImpl  implements ITestService {
	@Autowired
	private ITestDao dao;
	 
	public boolean addTest(TestInfo test) {
		return dao.addTest(test);
	}
}
