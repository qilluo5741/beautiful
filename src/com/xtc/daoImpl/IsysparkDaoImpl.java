package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IsysparkDao;
import com.xtc.entity.parking;
import com.xtc.entity.syspark;
import com.xtc.entity.dto.parkinfoDto;
@Repository
public class IsysparkDaoImpl extends HibernateDaoSupport implements IsysparkDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 停车场模糊查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<parkinfoDto> selectByNameorAddress(String name) {
		List<parkinfoDto> list = this.getSessionFactory().getCurrentSession().createQuery("from sysparking where name like '%"+name+"%' or address like '%"+name+"%'").list();
		return list;
	}
	/**
	 * 停车场信息查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<syspark> getParkcreat(String parkid) {
		List<syspark> list=getSessionFactory().openSession().createQuery("from syspark where id=?").setString(0,parkid).list();
		return list;
	}
	@Override
	public boolean updatesyspark(String city,String entry_address,String address, String capacity, String carnum,String type, String price, String is_cooperate, String name, String cost, String reservation,String dividedinto,double subscription,String parkid) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update syspark set city=?,entry_address=?,address=?,capacity=?,carnum=?,type=?,price=?,cost=?,is_cooperate=?,name=?,reservation=?,dividedinto=?,subscription=? where id=?")
			.setString(0,city)
			.setString(1,entry_address)
			.setString(2,address)
			.setString(3,capacity)
			.setString(4,carnum)
			.setString(5,type)
			.setString(6,price)
			.setString(7,cost)
			.setString(8,is_cooperate)
			.setString(9,name)
			.setString(10,reservation)
			.setString(11,dividedinto)
			.setDouble(12,subscription)
			.setString(13,parkid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * addtcc
	 */
	@Override
	public boolean addsyspark(parking park) {
		try {
			this.getSessionFactory().getCurrentSession().save(park);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
