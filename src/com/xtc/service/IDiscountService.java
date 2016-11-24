package com.xtc.service;

import java.util.List;

import com.xtc.entity.Discount;

public interface IDiscountService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Discount> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public Discount selectOneBydid(String did);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(Discount discount);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateDiscount(Discount discount);
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteDiscount(String did);
}
