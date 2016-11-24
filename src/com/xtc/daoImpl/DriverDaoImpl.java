package com.xtc.daoImpl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IDriverDao;
import com.xtc.entity.Driver;

@Repository
public class DriverDaoImpl extends HibernateDaoSupport implements IDriverDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Driver getOne(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Driver where userId=?").setString(0,userid);
		Driver d = (Driver) query.uniqueResult();
		return d;	
	}
	@Override
	public boolean updateDriver(Driver d) {
		this.getSessionFactory().getCurrentSession().update(d);
		return true;
	}
	@Override
	public boolean create(Driver d) {
		this.getSessionFactory().getCurrentSession().save(d);
		return true;
	}

}
