
package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IparkexpenseDao;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.parkexpense;
import com.xtc.entity.parkexpenses;
import com.xtc.entity.parking;
import com.xtc.entity.sysuser;
import com.xtc.util.Pager;
@Repository
public class ParkexpenseDaoImpl extends HibernateDaoSupport implements IparkexpenseDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean addparkexpense(parkexpense expense) {
		try {
			this.getSessionFactory().getCurrentSession().save(expense);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<parkexpense> selectByexpense(String parkid,String userid,int pageIndex,int pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from parkexpense where parkid=?")
		.setString(0,parkid);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		List<parkexpense> list = query.list();
		return list;
	}
	@Override
	public sysuser selectByuserid(String mobile) {
		return (sysuser) this.getSessionFactory().getCurrentSession().createQuery("from sysuser where mobile=?").setString(0,mobile).uniqueResult();
	}
	@Override
	public boolean updateByparkexpense(String chargestatus,String userid, String parkdeid, String factorytime) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update parkexpense set chargestatus=?,userid=?,factorytime=?,paymentstatus='1' where parkdeid=?")
			.setString(0,chargestatus)
			.setString(1,userid)
			.setString(2,factorytime)
			.setString(3,parkdeid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Orderinfo selectByOrder(String order_num) {
		return (Orderinfo) this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where order_num=?").setString(0,order_num).uniqueResult();
	}
	@Override
	public String selectBymobile(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from sysuser where id=?").setString(0,userid);
		String mobile=(String)query.uniqueResult();
		return mobile;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectByexpenseparkexpense(String sysuserid,Integer pageIndex,Integer pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("SELECT p.parkdeid, p.licenseplate, p.expense, p.receivable, p.firsttime,p.factorytime,p.paymentstatus,p.ordernumber,s.name,s.entry_address FROM parkexpense p,Park s WHERE sysuserid =? AND s.id=p.parkid ORDER BY firsttime DESC").setString(0,sysuserid);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		List<Object> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleLicense> selectByplate_no(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from VehicleLicense where userid=?")
		.setString(0,userid);
		List<VehicleLicense> list = query.list();
		return list;
	}
	@Override
	public boolean updateByparkuserid(String sysuserid, String licenseplate) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update parkexpense set sysuserid=? where licenseplate=?")
			.setString(0,sysuserid)
			.setString(1,licenseplate)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateBypaymentstatus(String paymentstatus, String ordernumber) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update parkexpense set paymentstatus=? where ordernumber=?")
			.setString(0,paymentstatus)
			.setString(1,ordernumber)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public parkexpense getorderuserandparkid(String ordernumber) {
		return (parkexpense) this.getSessionFactory().getCurrentSession().createQuery("from parkexpense where ordernumber=?").setString(0,ordernumber).uniqueResult();
	}
	/**
	 * 停车场报表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<parkexpenses> selectpgeryexpense(String mobile,Integer pageIndex, Integer pageSize) {
		Pager<parkexpenses> pager=new Pager<parkexpenses>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<parkexpenses> list=this.getSessionFactory().getCurrentSession().createQuery("from parkexpenses where mobile=?").setString(0,mobile).setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from parkexpenses where mobile=?").setString(0,mobile).uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
	@Override
	public Long selectByparkexpensescount(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select count(*) from parkexpense p,parking s WHERE p.parkid = s.id AND p.parkid =(select parkId from sysuser where mobile=?)").setString(0,mobile);
		Long cont=(Long)query.uniqueResult();
		return cont;
	}
	@Override
	public boolean addparkexpenses(parkexpenses expense) {
		try {
			this.getSessionFactory().getCurrentSession().save(expense);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public parking selectByparking(String parkid) {
		return (parking) this.getSessionFactory().getCurrentSession().createQuery("from parking where id=?").setString(0,parkid).uniqueResult();
	}
	@Override
	public sysuser selectByparkmobileing(String userid) {
		return (sysuser) this.getSessionFactory().getCurrentSession().createQuery("from sysuser where id=?").setString(0,userid).uniqueResult();
	}
	@Override
	public parkexpense getorderuserandparkid(String plate_no, String datecreat) {
		return (parkexpense) this.getSessionFactory().getCurrentSession().createQuery("from parkexpense where licenseplate=? and factorytime=?").setString(0,plate_no).setString(1,datecreat).uniqueResult();
	}
}
