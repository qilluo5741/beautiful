package com.xtc.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.RelatedrecordDao;
import com.xtc.entity.Relatedrecord;
@Repository
public class RelatedrecordDaoImpl extends HibernateDaoSupport implements RelatedrecordDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addRelatedrecord(Relatedrecord related) {
		try {
			this.getSessionFactory().getCurrentSession().save(related);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
