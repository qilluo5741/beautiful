package com.xtc.dao;

import java.util.List;

import com.xtc.entity.DrawcashState;

public interface IDrawcashStateDao {
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
	 * �������ּ�¼��id�鿴״̬��¼��
	 * @return
	 */
	public DrawcashState selectBydid(String did);
}
