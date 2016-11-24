package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IinvitationDao;
import com.xtc.entity.invitation;
@Repository
public class invitationDaoImpl extends HibernateDaoSupport implements
		IinvitationDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(invitation invitation) {
		try {
			this.getSessionFactory().getCurrentSession().save(invitation);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateByUserid(String date_created,String rtartmoble) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update invitation set date_created=?,state=1 where rtartmoble=?")
			.setString(0,date_created)
			.setString(1,rtartmoble)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<invitation> selectBymobile(String mobile) {
		List<invitation> list=this.getSessionFactory().getCurrentSession().createQuery("from invitation where startmoble=?").setString(0, mobile).list();
		return list;
	}
}
