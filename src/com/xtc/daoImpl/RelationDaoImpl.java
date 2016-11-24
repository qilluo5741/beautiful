package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IrelationDao;
import com.xtc.entity.relation;
import com.xtc.util.Pager;
@Repository
public class RelationDaoImpl extends HibernateDaoSupport implements IrelationDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<relation> selectAll() {
		List<relation> list = this.getSessionFactory().getCurrentSession().createQuery("from relation").list();
		return list;
	}
	@Override
	public boolean insert(relation relation) {
		try {
			this.getSessionFactory().getCurrentSession().save(relation);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public relation selectinvideCode(String invideCode) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from relation where invideCode=?").setString(0, invideCode);
		relation relation=(relation) query.uniqueResult();
		return relation;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<relation> selectAll(int pageIndex, int pageSize){
		Pager<relation> pager=new Pager<relation>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<relation> list=this.getSessionFactory().getCurrentSession().createQuery("from relation").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from relation").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
}
