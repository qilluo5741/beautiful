package com.xtc.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IAdviseDao;
import com.xtc.entity.Advise;
@Repository
public class AdviseDaoImpl extends HibernateDaoSupport implements IAdviseDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(Advise advise) {
		try {
			this.getSessionFactory().getCurrentSession().save(advise);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

}
