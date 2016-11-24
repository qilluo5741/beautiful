package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IAdvertisementDao;
import com.xtc.entity.Advertisement;

@Repository
public class AdvertisementDaoImpl extends HibernateDaoSupport implements IAdvertisementDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Advertisement> listALL() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Advertisement");
		List<Advertisement> list = query.list();
		return list;
	}
	@Override
	public boolean insert(Advertisement advert) {
		try {
			this.getSessionFactory().getCurrentSession().save(advert);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
