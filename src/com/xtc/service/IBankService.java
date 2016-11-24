package com.xtc.service;

import java.util.List;

import com.xtc.entity.Bank;

public interface IBankService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Bank> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public Bank selectOneBybid(String bid);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(Bank bank);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateBank( String accountname , String accountnumbe,String bankname ,
		    String bankplace , String subbank ,String userid );
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteBank(String userid);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	public Bank selectByUserid(String userid);
	public List<String> getuserids();
}
