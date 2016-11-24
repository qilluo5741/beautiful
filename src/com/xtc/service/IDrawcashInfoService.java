package com.xtc.service;

import java.util.List;

import com.xtc.entity.DrawcashInfo;

public interface IDrawcashInfoService {
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
	 * ����bankid�鿴һ������
	 * @return
	 */
	public DrawcashInfo selectBybankid(String bankid);
	/**
	 * ����userid�鿴һ������
	 * @param userid
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
	 */
	//public List<Object> selectObject();
}
