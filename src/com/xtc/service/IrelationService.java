package com.xtc.service;

import java.util.List;

import com.xtc.entity.relation;
import com.xtc.util.Pager;

public interface IrelationService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<relation> selectAll();
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(relation relation);
	/**
	 * 
	 * ����invideCode��ѯ
	 * @param invideCode
	 * @return
	 */
	public relation selectinvideCode(String invideCode);
	/**
	 * 	��ҳ��ѯ
	 */
	public Pager<relation> selectAll(int pageIndex, int pageSize);
}
