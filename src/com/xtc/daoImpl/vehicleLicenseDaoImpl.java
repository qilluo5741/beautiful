package com.xtc.daoImpl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IvehicleLicenseDao;
import com.xtc.entity.VehicleLicense;
@Repository
public class vehicleLicenseDaoImpl extends HibernateDaoSupport implements IvehicleLicenseDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public VehicleLicense selectByplateNo(String plateNo) {
		return (VehicleLicense) this.getSessionFactory().getCurrentSession().createQuery("from VehicleLicense where plate_no=?").setString(0,plateNo).uniqueResult();
	}
	/*@Override
	public List<VehicleLicense> selectByplateNo(String plateNo) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from VehicleLicense where plateNo=?").setString(0,plateNo);
		List<VehicleLicense> list=query.list();
		return list;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleLicense> getByUserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from VehicleLicense where userId=?").setString(0,userid);
		List<VehicleLicense> list=query.list();
		return list;
	}

	@Override
	public boolean delete(String userid) {
		try {
			this.getSessionFactory().getCurrentSession().createQuery("delete VehicleLicense where userId=?").setString(0,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public VehicleLicense getVL(String userid, String plate_no) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from VehicleLicense where userId=? and plate_no=?")
				.setString(0,userid)
				.setString(1,plate_no);
		VehicleLicense vl = (VehicleLicense) query.uniqueResult();
		return vl;
	}

	@Override
	public boolean create(VehicleLicense vl) {
		this.getSessionFactory().getCurrentSession().save(vl);
		return true;
	}

	@Override
	public boolean update(VehicleLicense vl) {
		this.getSessionFactory().getCurrentSession().update(vl);
		return true;
	}
}
