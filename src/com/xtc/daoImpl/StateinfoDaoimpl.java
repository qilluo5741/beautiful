package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IStateinfoDao;
import com.xtc.entity.Stateinfo;
@Repository
public class StateinfoDaoimpl extends HibernateDaoSupport implements IStateinfoDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Stateinfo> selectAll(String parkid) {
		List<Stateinfo> list = this.getSessionFactory().getCurrentSession().createQuery("from Stateinfo where parkid=? order by statetime desc").setString(0, parkid).setMaxResults(30).list();
		return list;
	}
	@Override
	public Stateinfo selectOneBysid(String sid) {
		return null;
	}

	@Override
	public boolean insert(Stateinfo stateinfo) {
		try {
			this.getSessionFactory().getCurrentSession().save(stateinfo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStateinfo() {
		return false;
	}

	@Override
	public boolean deleteStateinfo(String userid) {
		return false;
	}
}
