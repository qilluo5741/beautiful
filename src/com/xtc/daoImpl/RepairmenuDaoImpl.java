package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IRepairmenuDao;
import com.xtc.entity.Repairmenu;
@Repository
public class RepairmenuDaoImpl extends HibernateDaoSupport implements IRepairmenuDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Repairmenu> getRepairmenuAll() {
		List<Repairmenu> list = this.getSessionFactory().getCurrentSession().createQuery("from Repairmenu").list();
		return list;
	}

}
