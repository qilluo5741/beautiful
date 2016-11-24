package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.ITagDao;
import com.xtc.entity.Tag;

@Repository
public class TagDaoImpl  extends HibernateDaoSupport implements ITagDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> taglist(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Tag where userId=?").setString(0,userid);
		List<Tag> list=query.list();
		return list;
	}

	@Override
	public boolean delete(String userid) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("delete Tag where userId=?").setString(0,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Tag getTag(String userid, String name) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from  Tag where userId=? and name = ?")
				.setString(0,userid)
				.setString(1, name);
		Tag t = (Tag) query.uniqueResult();
		return t;
	}

	@Override
	public boolean create(Tag tag) {
		this.getSessionFactory().getCurrentSession().save(tag);
		return true;
	}

	@Override
	public boolean update(Tag t) {
		this.getSessionFactory().getCurrentSession().update(t);
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> taglist(String userid, String type) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Tag where userId=? and type = ?").setString(0,userid).setString(1,type);
		List<Tag> list = query.list();
		return list;
	}
}
	

