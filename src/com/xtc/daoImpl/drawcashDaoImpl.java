package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IdrawcashDao;
import com.xtc.entity.drawcash;
import com.xtc.util.Pager;
@Repository
public class drawcashDaoImpl extends HibernateDaoSupport implements
		IdrawcashDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<drawcash> selectAll(int pageIndex, int pageSize) {
		Pager<drawcash> pager=new Pager<drawcash>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<drawcash> list=this.getSessionFactory().getCurrentSession().createQuery("from drawcash").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from drawcash").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
	@Override
	public boolean updateState(String finaltime,String id) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update drawcash set state=0,finaltime=?"
					+ " where drawid=?")
			.setString(0,finaltime)
			.setString(1,id)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
