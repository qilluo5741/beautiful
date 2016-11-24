package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.GoodrepairDao;
import com.xtc.entity.Goodrepair;
@Repository
public class GoodrepairDaoImpl extends HibernateDaoSupport implements GoodrepairDao {
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Goodrepair> selectGoodrepairAll(String goodproperid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Goodrepair where goodproperid=?").setString(0,goodproperid);
		List<Goodrepair> list = query.list();
		return list;
	}
}
