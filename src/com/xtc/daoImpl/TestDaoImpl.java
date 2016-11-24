package com.xtc.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.ITestDao;
import com.xtc.entity.TestInfo;
 
@Repository
public class TestDaoImpl extends HibernateDaoSupport implements ITestDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	public boolean addTest(TestInfo test){
		Session session=getSessionFactory().getCurrentSession();
		session.save(test);
		return true;
	}
}
