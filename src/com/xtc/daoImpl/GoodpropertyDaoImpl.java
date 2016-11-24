package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IgoodpropertyDao;
import com.xtc.entity.goodproperty;
@Repository
public class GoodpropertyDaoImpl extends HibernateDaoSupport implements IgoodpropertyDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<goodproperty> selectgetgoodpropertyAll(String repmenuid) {
		List<goodproperty> list=this.getSessionFactory().getCurrentSession().createQuery("from goodproperty where repmenuid=?").setString(0,repmenuid).list();
		return list;
	}
}
