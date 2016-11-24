package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IjpushinfoDao;
import com.xtc.entity.jpushinfo;
@Repository
public class jpushinfoDaoImpl extends HibernateDaoSupport implements IjpushinfoDao {
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public  List<jpushinfo> selectByUserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from jpushinfo where userid=?").setString(0,userid);
		List<jpushinfo> list =query.list();
		return list;
	}
	@Override
	public boolean insert(jpushinfo jpush) {
		try {
			this.getSessionFactory().getCurrentSession().save(jpush);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updatejpushinfo(String regid, String userid) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update jpushinfo set regid=? where userid=?")
			.setString(0,regid)
			.setString(1,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String selectByregid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select regid from jpushinfo where userid=?").setString(0,userid);
		String jpush=(String)query.uniqueResult();
		return jpush;
	}
}
