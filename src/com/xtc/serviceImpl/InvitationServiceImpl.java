package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IinvitationDao;
import com.xtc.entity.invitation;
import com.xtc.service.IinvitationService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class InvitationServiceImpl implements IinvitationService {
	@Autowired
	private IinvitationDao dao;
	@Override
	public boolean insert(invitation invitation) {
		return dao.insert(invitation);
	}
	@Override
	public boolean updateByUserid(String date_created,String rtartmoble) {
		return dao.updateByUserid(date_created,rtartmoble);
	}
	@Override
	public List<invitation> selectBymobile(String mobile) {
		return dao.selectBymobile(mobile);
	}
}
