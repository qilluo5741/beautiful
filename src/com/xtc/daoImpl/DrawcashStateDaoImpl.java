package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IDrawcashStateDao;
import com.xtc.entity.DrawcashState;

@Repository
public class DrawcashStateDaoImpl extends HibernateDaoSupport implements IDrawcashStateDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<DrawcashState> selectAll() {
		return null;
	}

	@Override
	public DrawcashState selectOneBybid(String accid) {
		return null;
	}

	@Override
	public boolean insert(DrawcashState DrawcashState) {
		try {
			this.getSessionFactory().getCurrentSession().save(DrawcashState);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDrawcashState(){
		return false;
	}

	@Override
	public boolean deleteDrawcashState(String userid) {
		return false;
	}

	@Override
	public DrawcashState selectBydid(String did) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from DrawcashState where did=?").setString(0,did);
		DrawcashState state  = (DrawcashState) query.uniqueResult();
		return state;	
	}
}
