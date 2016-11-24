package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Stateinfo;

public interface IStateinfoDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Stateinfo> selectAll(String parkid);
	/**
	 * ������ѯ
	 * @return
	 */
	public Stateinfo selectOneBysid(String sid);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(Stateinfo stateinfo);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateStateinfo();
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteStateinfo(String userid);
	
}
