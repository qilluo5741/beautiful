package com.xtc.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.BonusshareDao;
import com.xtc.entity.bonusshare;
@Repository
public class BonusshareDaoImpl extends HibernateDaoSupport implements BonusshareDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(bonusshare b) {
		try {
			this.getSessionFactory().getCurrentSession().save(b);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public bonusshare selectByMobile(String mobile,String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from bonusshare where bonuscount>0 and order_num=? and bonusmobile=?")
				.setString(0,order_num)
				.setString(1,mobile);
		bonusshare bonus=(bonusshare) query.uniqueResult();
		return bonus;	
	}
	@Override
	public boolean updateCount(int bonuscount,String mobile,String bonusdate){
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update bonusshare set bonuscount=? where bonusdate=? and bonusmobile=?")
			.setInteger(0,bonuscount)
			.setString(1,bonusdate)
			.setString(2, mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public int selectbonuscount(String mobile,String bonusdate) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select bonuscount from bonusshare where bonusdate=? and bonusmobile=?")
		.setString(0,bonusdate)
		.setString(1,mobile);
		int bonuscount=(int)query.uniqueResult();
		return bonuscount;
	}
	
}
