package com.xtc.service;

import java.util.List;

import com.xtc.entity.DrawcashState;

public interface IDrawcashStateService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<DrawcashState> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public DrawcashState selectOneBybid(String accid);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(DrawcashState DrawcashState);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateDrawcashState( );
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteDrawcashState(String userid);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	public DrawcashState selectBydid(String did);
}
