package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IbalancedetailDao;
import com.xtc.entity.balancedetail;
import com.xtc.util.Pager;
@Repository
public class balancedetailDao extends HibernateAccessor implements IbalancedetailDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(balancedetail balan) {
		try {
			this.getSessionFactory().getCurrentSession().save(balan);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<balancedetail> selectByuserid(String userid,int pageIndex,int pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from balancedetail where userId=? and balancetype !='7' order by starttime desc").setString(0,userid);
		query.setFirstResult(pageIndex * pageSize);  
		query.setMaxResults(pageSize);
		List<balancedetail> list  =query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public  Pager<balancedetail> selectAll(int pageIndex, int pageSize) {
		Pager<balancedetail> pager=new Pager<balancedetail>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<balancedetail> list=this.getSessionFactory().getCurrentSession().createQuery("from balancedetail").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from balancedetail").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
	@Override
	public String selectBybalancetype(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select balancetype from balancedetail where balancetype='7' and userid=?").setString(0,userid);
		String balancetype=(String)query.uniqueResult();
		return balancetype;
	}
	@Override
	public boolean updateBybalancetype(String balancetype,String userid) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update balancedetail set balancetype=? where money=10 and balancetype = '7' and userid=?")
			.setString(0,balancetype)
			.setString(1,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
