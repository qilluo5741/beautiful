package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.PaymentrecordsDao;
import com.xtc.entity.paymentrecords;
@Repository
public class PaymentrecordsDaoImpl extends HibernateDaoSupport implements PaymentrecordsDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addPaymentrecord(paymentrecords payment) {
		try {
			this.getSessionFactory().getCurrentSession().save(payment);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<paymentrecords> selectBypaymentrecords(String userid,Integer pageIndex,Integer pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from  paymentrecords where userid=? order by paymenttime desc").setString(0,userid);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		List<paymentrecords> list = query.list();
		return list;
	}
}
