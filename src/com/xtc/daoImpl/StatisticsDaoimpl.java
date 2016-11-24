package com.xtc.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IstatisticsDao;
import com.xtc.entity.statistics;
@Repository
public class StatisticsDaoimpl extends HibernateDaoSupport implements IstatisticsDao {
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public statistics selectBystatisticcount(String statisticid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from statistics where statisticid=?").setString(0,statisticid);
		statistics statistics=(statistics)query.uniqueResult();
		return statistics;	
	}

	@Override
	public boolean updateByselectBystatisticcount(int statisticcount, String statisticid) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update statistics a set statisticcount=? where statisticid=?")
			.setInteger(0,statisticcount)
			.setString(1,statisticid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

}
