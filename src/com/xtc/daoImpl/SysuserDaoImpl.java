package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.SysuserDao;
import com.xtc.entity.Park;
import com.xtc.entity.sysuser;
@Repository
public class SysuserDaoImpl extends HibernateDaoSupport implements SysuserDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public sysuser getsysuserInfo(String mobile,String password) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from sysuser where mobile=? and password=? and isdelete=0 and property='wy'").setString(0,mobile).setString(1,password);
		sysuser sysuser = (sysuser) query.uniqueResult();
		return sysuser;
	}
	@Override
	public boolean updatesysuserpwd(String first,String password,String mobile) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update sysuser set first=?,password=? where mobile=?")
			.setString(0,first)
			.setString(1,password)
			.setString(2,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public sysuser selectsysuserfirst(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from sysuser where mobile=?").setString(0,mobile);
		sysuser sysu = (sysuser) query.uniqueResult(); 
		return sysu;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> selectByParkName(String name) {
		List<Park> list = this.getSessionFactory().getCurrentSession().createQuery("from Park where name like '%"+name+"%' or address like '%"+name+"%'").list();
		return list;
	}
	@Override
	public boolean addsysuser(sysuser sysuser){
		try {
			this.getSessionFactory().getCurrentSession().save(sysuser);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public sysuser sysusermobile(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from sysuser where property='batp' and mobile=?").setString(0,mobile);
		sysuser user = (sysuser) query.uniqueResult(); 
		return user;
	}
	@Override
	public String selectmobile(String mobile){
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from sysuser where mobile=?").setString(0,mobile);
		String mobil=(String)query.uniqueResult();
		return mobil;
	}
	@Override
	public boolean updatesysuserrelation(String parkid,String isdelete,String property,String name, String mobile,String is_admin) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update sysuser set parkid=?,isdelete=?,property=?,name=?,is_admin=? where mobile=?")
			.setString(0,parkid)
			.setString(1,isdelete)
			.setString(2,property)
			.setString(3,name)
			.setString(4,is_admin)
			.setString(5,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateparkuser(String userid, String parkid) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update Park set userid=? where id=?")
			.setString(0,userid)
			.setString(1,parkid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectusermobile(String parkid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select name,mobile from sysuser where parkid=? and is_admin='2'").setString(0,parkid);
		List<Object> list  =query.list();
		return list;
	}
	@Override
	public boolean updatesysuserrelation(String is_admin, String parkId, String mobile) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update sysuser set is_admin=?,parkId=? where mobile=?")
			.setString(0,is_admin)
			.setString(1,parkId)
			.setString(2,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updatesysuserrelations(String is_admin, String parkId, String isdelete, String mobile) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update sysuser set is_admin=?,parkId=?,isdelete=? where mobile=?")
			.setString(0,is_admin)
			.setString(1,parkId)
			.setString(2,isdelete)
			.setString(3,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<sysuser> selectsysusermobilet(String parkid){
		List<sysuser> list = this.getSessionFactory().getCurrentSession().createQuery("from sysuser where parkid=?").setString(0,parkid).list();
		return list;
	}
}
