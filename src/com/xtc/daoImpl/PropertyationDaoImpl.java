package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.propertyationDao;
import com.xtc.entity.propertyation;
@Repository
public class PropertyationDaoImpl extends HibernateDaoSupport implements propertyationDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addpropertyation(propertyation property) {
		try {
			this.getSessionFactory().getCurrentSession().save(property);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<propertyation> selectpropertyation(String parkid) {
		List<propertyation> list = this.getSessionFactory().getCurrentSession().createQuery("from propertyation where parkid=?").setString(0,parkid).list();
		return list;
	}
}
