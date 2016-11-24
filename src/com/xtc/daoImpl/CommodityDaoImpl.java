package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IcommodityDao;
import com.xtc.entity.commodity;
@Repository
public class CommodityDaoImpl extends HibernateDaoSupport implements IcommodityDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<commodity> selectgetcommodityAll(String goodproperid) {
		List<commodity> list=this.getSessionFactory().getCurrentSession().createQuery("from commodity where goodproperid=?").setString(0,goodproperid).list();
		return list;
	}
	@Override
	public boolean updatecommodity(String commodityid, String commodicount) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update commodity set commodicount=? where commodityid=?")
			.setString(0,commodicount)
			.setString(1,commodityid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

}
