package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.tollRecordsDao;
import com.xtc.entity.sysuser;
import com.xtc.entity.tollRecords;
@Repository
public class tollRecordsDaoImpl extends HibernateDaoSupport implements tollRecordsDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addtollRecords(tollRecords tollrecord) {
		try {
			this.getSessionFactory().getCurrentSession().save(tollrecord);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<tollRecords> selecttollRecords(String tollRphone) {
		List<tollRecords> list = this.getSessionFactory().getCurrentSession().createQuery("from tollRecords where tollRphone=?").setString(0,tollRphone).list();
		return list;
	}
	@Override
	public boolean updatetollRecords(String password, String mobile) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("update sysuser set password=? where mobile=?").setString(0,password).setString(1,mobile).executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String selectBymobile(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from sysuser where mobile=?").setString(0,mobile);
		String mobil=(String)query.uniqueResult();
		return mobil;
	}
	@Override
	public String selectBymobiles(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from sysuser where mobile=? and property='wy'").setString(0,mobile);
		String user=(String)query.uniqueResult();
		return user;
	}
	@Override
	public boolean updateTollRecord(String name, String mobile, String password, String age, String is_owner,String is_admin, String first, String property, String isdelete, String parkid) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update sysuser set name=?,age=?,is_owner=?,is_admin=?,first=?,property=?,isdelete=?,parkid=? where mobile=?")
			.setString(0,name)
			.setString(1,age)
			.setString(2,is_owner)
			.setString(3,is_admin)
			.setString(4,first)
			.setString(5,property)
			.setString(6,isdelete)
			.setString(7,parkid)
			.setString(8,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delectTollRecord(String isdelete, String mobile) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update sysuser set isdelete=? where mobile=?")
			.setString(0,isdelete)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public sysuser selectByParkid(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from sysuser where mobile=?").setString(0,mobile);
		sysuser sysuser = (sysuser) query.uniqueResult();
		return sysuser;
	}
	@Override
	public boolean deleteTollRecord(String tollRrecphone) {
		try {
			getSessionFactory().getCurrentSession().createQuery("delete tollRecords t where t.tollRrecphone = :tollRrecphone")
			.setString("tollRrecphone",tollRrecphone)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteTollRecords(String mobile) {
		try {
			getSessionFactory().getCurrentSession().createQuery("delete sysuser s where s.mobile = :mobile")
			.setString("mobile",mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<tollRecords> slectTollRecords(String parkid) {
		List<tollRecords> list = this.getSessionFactory().getCurrentSession().createQuery("from tollRecords where parkid=?").setString(0,parkid).list();
		return list;
	}
	@Override
	public String selectBymtollRrecphone(String tollRrecphone) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select tollRrecphone from tollRecords where tollRrecphone=?").setString(0,tollRrecphone);
		String mobile=(String)query.uniqueResult();
		return mobile;
	}
}
