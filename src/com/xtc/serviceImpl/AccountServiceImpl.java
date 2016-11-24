package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IAccountDao;
import com.xtc.entity.Account;
import com.xtc.service.IAccountService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private IAccountDao dao;
	@Override
	public List<Account> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Account selectOneByaccid(String accid) {
		return dao.selectOneByaccid(accid);
	}

	@Override
	public boolean insert(Account account) {
		return dao.insert(account);
	}

	@Override
	public boolean updateAccount(Account account) {
		return dao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(String accid) {
		return dao.deleteAccount(accid);
	}

	@Override
	public Account selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public boolean updateByUserid(String userid, double balance) {
		return dao.updateByUserid(userid, balance);
	}
}
