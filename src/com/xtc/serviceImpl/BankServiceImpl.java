package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IBankDao;
import com.xtc.entity.Bank;
import com.xtc.service.IBankService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class BankServiceImpl implements IBankService{
	@Autowired
	private IBankDao dao;
	@Override
	public List<Bank> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Bank selectOneBybid(String bid) {
		return dao.selectOneBybid(bid);
	}

	@Override
	public boolean insert(Bank bank) {
		return dao.insert(bank);
	}

	@Override
	public boolean updateBank(String accountname , String accountnumbe,String bankname ,
		    String bankplace , String subbank ,String userid ) {
		return dao.updateBank(accountname ,accountnumbe, bankname ,
			    bankplace ,  subbank , userid );
	}
	@Override
	public boolean deleteBank(String userid) {
		return dao.deleteBank(userid);
	}

	@Override
	public Bank selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public List<String> getuserids() {
		return dao.getuserids();
	}
}
