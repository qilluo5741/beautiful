package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IorderparkDao;
import com.xtc.entity.orderpark;
@Repository
public class OrderparkDaoImpl extends HibernateDaoSupport implements IorderparkDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(orderpark orderpark) {
		try {
			this.getSessionFactory().getCurrentSession().save(orderpark);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateorderpark(String evaluate,String pinId) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update orderpark set evaluate=? where pinId=?")
			.setString(0,evaluate)
			.setString(1,pinId)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateorderparkr(String evaluatr, String pinId) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update orderpark set evaluatr=? where pinId=?")
			.setString(0,evaluatr)
			.setString(1,pinId)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<orderpark> selectBymobile(String usname) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from orderpark where usname=?").setString(0,usname);
		List<orderpark> list = query.list();
		return list;
	}
}
