package com.xtc.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.OrdersecurityDao;
import com.xtc.entity.Ordersecurity;
@Repository
public class OrdersecurityDaoImpl extends HibernateDaoSupport implements OrdersecurityDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addOrdersecurity(Ordersecurity order) {
		try {
			this.getSessionFactory().getCurrentSession().save(order);
			return true;
		} catch (HibernateException e){
			e.printStackTrace();
		}
		return false;
	}
}
