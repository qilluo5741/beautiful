package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IBankDao;
import com.xtc.entity.Bank;
@Repository
public class BankDaoImpl extends HibernateDaoSupport implements IBankDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Bank> selectAll() {
		return null;
	}

	@Override
	public Bank selectOneBybid(String bid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Bank b where b.bid=? ").setString(0,bid);
		Bank bank=(Bank) query.uniqueResult();
		return bank;	
	}

	@Override
	public boolean insert(Bank bank) {
		try {
			this.getSessionFactory().getCurrentSession().save(bank);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBank( String accountname , String accountnumbe,String bankname ,
		    String bankplace , String subbank ,String userid ){
		
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Bank set accountname=?,"
					+ "accountnumber=?,bankname=?,bankplace=?,subbank=?"
					+ " where userid=?")
			.setString(0,accountname)
			.setString(1,accountnumbe)
			.setString(2,bankname)
			.setString(3,bankplace)
			.setString(4,subbank)
			.setString(5,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBank(String userid) {
		try {
			getSessionFactory().getCurrentSession().createQuery("delete Bank b where b.userid=?")
			.setString(0, userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Bank selectByUserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Bank where userid=?").setString(0,userid);
		Bank bank=(Bank) query.uniqueResult();
		return bank;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getuserids() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userid from Bank");
		 List<String> list = query.list();
		return list;	
	}
	
}
