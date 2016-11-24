package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.ToolversionDao;
import com.xtc.entity.toolversion;
@Repository
public class ToolversionDaoImpl extends HibernateDaoSupport implements ToolversionDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<toolversion> selectByToolversion() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from toolversion where toonumber='android'");
		List<toolversion> list = query.list();
		return list;
	}
}
