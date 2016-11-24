package com.xtc.service;

import java.util.List;

import com.xtc.entity.Account;

public interface IAccountService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Account> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public Account selectOneByaccid(String accid);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(Account account);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateAccount(Account account);
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteAccount(String accid);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	public Account selectByUserid(String userid);
	/**
	 * ����userid���¿������
	 * @return
	 */
	public boolean  updateByUserid(String userid,double balance);
}
