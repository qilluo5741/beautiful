package com.xtc.daoImpl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IUserinfoDao;
import com.xtc.entity.Userinfo;
@Repository
public class UserinfoDaoImpl extends HibernateDaoSupport implements IUserinfoDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Userinfo login(String userName, String userPwd) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Userinfo where userName=? and userPwd=?")
		.setString(0,userName)
		.setString(1,userPwd);
		Userinfo u = (Userinfo) query.uniqueResult();
		return u;	
	}
}
