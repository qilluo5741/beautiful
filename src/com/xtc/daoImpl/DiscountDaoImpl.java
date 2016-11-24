package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IDiscountDao;
import com.xtc.entity.Discount;
@Repository
public class DiscountDaoImpl extends HibernateDaoSupport implements IDiscountDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> selectAll() {
		List<Discount> list = this.getSessionFactory().getCurrentSession().createQuery("from Discount").list();
		return list;
	}

	@Override
	public Discount selectOneBydid(String did) {
		return null;
	}

	@Override
	public boolean insert(Discount discount) {
		return false;
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		return false;
	}

	@Override
	public boolean deleteDiscount(String did) {
		return false;
	}
}
