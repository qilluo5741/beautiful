package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IAccountDao;
import com.xtc.entity.Account;
@Repository
public class AccountDaoImpl extends HibernateDaoSupport implements IAccountDao{
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> selectAll() {
		List<Account> list = this.getSessionFactory().getCurrentSession().createQuery("from Account").list();
		return list;
	}

	@Override
	public Account selectOneByaccid(String accid) {
		return  (Account) this.getSessionFactory().getCurrentSession().get(Account.class,accid);
	}

	@Override
	public boolean insert(Account account) {
		try {
			this.getSessionFactory().getCurrentSession().save(account);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		return false;
	}

	@Override
	public boolean deleteAccount(String accid) {
		return false;
	}
	@Override
	public Account selectByUserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Account where userid=?").setString(0,userid);
		Account account=(Account)query.uniqueResult();
		return account;	
	}
	@Override
	public boolean  updateByUserid(String userid,double balance) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Account a set balance=? where userid=?")
			.setDouble(0,balance)
			.setString(1,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
