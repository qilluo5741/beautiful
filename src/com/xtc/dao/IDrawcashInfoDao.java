package com.xtc.dao;

import java.util.List;

import com.xtc.entity.DrawcashInfo;

public interface IDrawcashInfoDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<DrawcashInfo> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public DrawcashInfo selectOneBydid(String did);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(DrawcashInfo drawcashInfo);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateDrawcashInfo();
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteDrawcashInfo(String userid);
	/**
	 * ����binkid�鿴һ������
	 * @return
	 */
	public DrawcashInfo selectBybankid(String binkid);
	/**
	 * ����userid�鿴һ������
	 * @return
	 */
	
	public List<DrawcashInfo> selectByUserid(String userid);
	/**
	 * ������ּ�¼
	 * @param drawid
	 * @return
	 */
	public DrawcashInfo selectBydrawid(String drawid);
	/**
	 *��ѯ���ּ�¼������֧������֧�����
	 *//*
	public List<Object> selectObject();*/
}
