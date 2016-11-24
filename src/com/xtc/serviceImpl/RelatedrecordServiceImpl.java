package com.xtc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.RelatedrecordDao;
import com.xtc.entity.Relatedrecord;
import com.xtc.service.RelatedrecordService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class RelatedrecordServiceImpl implements RelatedrecordService {
	@Autowired
	private RelatedrecordDao dao;
	@Override 
	public boolean addRelatedrecord(Relatedrecord related) {
		return dao.addRelatedrecord(related);
	}

}
