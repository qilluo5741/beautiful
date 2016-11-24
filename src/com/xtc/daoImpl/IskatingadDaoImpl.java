package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IskatingadDao;
import com.xtc.entity.skatingad;
@Repository
public class IskatingadDaoImpl extends HibernateDaoSupport implements IskatingadDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<skatingad> selectByskatingad() {
		List<skatingad> list=getSessionFactory().openSession().createQuery("from skatingad").list();
		return list;
	}
}
