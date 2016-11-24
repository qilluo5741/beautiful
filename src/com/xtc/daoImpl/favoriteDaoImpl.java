package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IfavoriteDao;
import com.xtc.entity.Park;
import com.xtc.entity.favorite;
@Repository
public class favoriteDaoImpl extends HibernateDaoSupport implements
		IfavoriteDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(favorite favorite) {
		try {
			this.getSessionFactory().getCurrentSession().save(favorite);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletefavorite(String parkid,String userId) {
		try {
			getSessionFactory().getCurrentSession().createQuery("delete from favorite where parkId=? and userId=?")
			.setString(0,parkid)
			.setString(1,userId)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<favorite> getFavorite(String userId) {
		List<favorite> list=getSessionFactory().openSession().createQuery("from favorite where userId=?").setString(0,userId).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> getPark(List<favorite> list) {
		//ƴװlist
		StringBuffer pakeIds=new StringBuffer();
		for (int i=0;i<list.size();i++) {
			pakeIds.append("'"+list.get(i).getParkId()+"'");
			if(i<list.size()-1){
				pakeIds.append(",");
			}
		}
		List<Park> plist=getSessionFactory().openSession().createQuery("from Park where id in ("+pakeIds+")").list();
		return plist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getfavorite(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select p.id, p.name,p.address "
				+ " from Park p ,favorite f where p.id=f.parkId and f.userId=?").setString(0,userid);
		List<Object> list  =query.list();
		return list;
	}
	@Override
	public favorite favor(String userId, String parkId) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from favorite where userId=? and parkId=?")
				.setString(0,userId)
				.setString(1,parkId);
		favorite favor = (favorite) query.uniqueResult();
		return favor;
	}
	@Override
	public favorite getParkfavorite(String parkid,String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from favorite where userId=? and parkId=?")
		.setString(0,userid)
		.setString(1,parkid);
		favorite favor = (favorite)query.uniqueResult();
		return favor;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getUserfavorite(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select p.id, p.name,p.address,f.location_x, f.location_y from Park p ,favorite f where p.id=f.parkId and f.userId=?").setString(0,userid);
		List<Object> list  =query.list();
		return list;
	}
}
