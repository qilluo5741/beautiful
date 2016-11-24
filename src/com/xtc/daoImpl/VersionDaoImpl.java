package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IVersionDao;
import com.xtc.entity.Version;
@Repository
public class VersionDaoImpl extends HibernateDaoSupport implements IVersionDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Version> getAll() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Version where os_version='android'");
		List<Version> list = query.list();
		return list;
	}

	@Override
	public boolean updateversion() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Version> getAlls() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Version where os_version='ios'");
		List<Version> list = query.list();
		return list;
	}

	@Override
	public boolean insertVersion(Version version) {
		try {
			this.getSessionFactory().getCurrentSession().save(version);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
