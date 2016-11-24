package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IredshareDao;
import com.xtc.entity.redshare;
@Repository
public class redshareDaoImpl extends HibernateDaoSupport implements IredshareDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(redshare redshare) {
		try {
			this.getSessionFactory().getCurrentSession().save(redshare);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<redshare> selectredshare(String sharemobile,String maxdate) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from redshare where sharedel=0 and shareend>? and sharemobile=?")
				.setString(0,maxdate)
				.setString(1,sharemobile);
		List<redshare> list  =query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectredshares(String sharemobile,String maxdate) {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery("select shareid,sharemoney from redshare where sharemoney=(select max(sharemoney)sharemoney from redshare where sharedel=0 and shareend>? and sharemobile=?)")
				.setString(0,maxdate)
				.setString(1,sharemobile);
		List<Object> list  =query.list();
		return list;
	}
	@Override
	public boolean updateOrdersharemoney(double sharemoney, String order_num) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set sharemoney=? where order_num=?")
			.setDouble(0,sharemoney)
			.setString(1,order_num)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<redshare> selectshareid(String shareid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from redshare where shareid=?").setString(0,shareid);
		List<redshare> list  =query.list();
		return list;
	}
	@Override
	public boolean updatesharedel(String shareid) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update redshare set sharedel=1 where shareid=?")
			.setString(0,shareid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<redshare> selectredmobilee(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from redshare where mobile=?").setString(0,mobile);
		List<redshare> list  =query.list();
		return list;
	}
	@Override
	public String selectbonuscount(String sharedate,String mobile,String sharemobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select shareid from redshare where sharedate=? and mobile=? and sharemobile=?")
		.setString(0,sharedate)
		.setString(1,mobile)
		.setString(2,sharemobile);
		String shareid =(String)query.uniqueResult();
		return shareid;
	}
	@Override
	public boolean deletesharemobile(String sharedate, String mobile) {
		return false;
	}
	@Override
	public redshare selectmoney(String mobile, String sharedate) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from redshare where sharedate=? and sharedel=0 and sharemobile=?")
		.setString(0,sharedate)
		.setString(1,mobile);
		redshare redshare=(redshare) query.uniqueResult();
		return redshare;
	}
	@Override
	public String selectOneByid(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select paystatus from Orderinfo where paystatus=1 and order_num=?").setString(0,order_num);
		String order=(String)query.uniqueResult();
		return order;
	}
}
