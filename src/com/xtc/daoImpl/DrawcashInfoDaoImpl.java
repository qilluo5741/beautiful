package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IDrawcashInfoDao;
import com.xtc.entity.DrawcashInfo;
@Repository
public class DrawcashInfoDaoImpl extends HibernateDaoSupport implements IDrawcashInfoDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<DrawcashInfo> selectAll() {
		return null;
	}

	@Override
	public DrawcashInfo selectOneBydid(String did) {
		return null;
	}

	@Override
	public boolean insert(DrawcashInfo drawcashInfo) {
		try {
			this.getSessionFactory().getCurrentSession().save(drawcashInfo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDrawcashInfo() {
		return false;
	}

	@Override
	public boolean deleteDrawcashInfo(String userid) {
		return false;
	}

	@Override
	public DrawcashInfo selectBybankid(String bankid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from DrawcashInfo d inner join Bank b where b.bid=?").setString(0,bankid);
		DrawcashInfo  dinfo = (DrawcashInfo) query.uniqueResult();
		return dinfo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DrawcashInfo> selectByUserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from DrawcashInfo d inner join fetch d.bank b where d.userid=?").setString(0,userid);
		List<DrawcashInfo> list = query.list();
		return list;
	}
	@Override
	public DrawcashInfo selectBydrawid(String drawid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from DrawcashInfo d where d.drawid=?").setString(0,drawid);
		DrawcashInfo draw=(DrawcashInfo) query.uniqueResult();
		return draw;
	}
}
