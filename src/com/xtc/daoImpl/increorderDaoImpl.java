package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IncreorderDao;
import com.xtc.entity.increorder;
@Repository
public class increorderDaoImpl extends HibernateDaoSupport implements IncreorderDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean Addincreorder(increorder increorder) {
		try {
			this.getSessionFactory().getCurrentSession().save(increorder);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> Increproperty(String userid, String repmenuid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("SELECT i.inorderid,i.inorderNo,i.inorderstatus,i.inordertime,g.goodproname FROM increorder i,goodproperty g WHERE i.goodproperid = g.goodproperid AND i.repmenuid = ? AND i.userid = ? and instatus>0")
		.setString(0,repmenuid)
		.setString(1,userid);
		List<Object> list  =query.list();
		return list;
	}
	@Override
	public boolean updateIncreorder(String instatus, String intime,double inordermoney,String inorderNo) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update increorder set instatus=?,intime=?,inordermoney=? where inorderNo=?")
			.setString(0,instatus)
			.setString(1,intime)
			.setDouble(2,inordermoney)
			.setString(3,inorderNo)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public  List<increorder> selectUserid(String inorderNo) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from increorder where inorderNo=?").setString(0,inorderNo);
		List<increorder> list = query.list();
		return list;
	}
}
