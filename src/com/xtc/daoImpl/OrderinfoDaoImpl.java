package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IOrderinfoDao;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.Park;
import com.xtc.entity.parking;
import com.xtc.util.Pager;
@Repository
public class OrderinfoDaoImpl extends HibernateDaoSupport implements IOrderinfoDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Orderinfo> selectAll() {
		return null;
	}
	@Override
	public Orderinfo selectOneByoid(String oid) {
		return null;
	}
	@Override
	public boolean insert(Orderinfo orderinfo) {
		try {
			this.getSessionFactory().getCurrentSession().save(orderinfo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateOrderinfo() {
		return false;
	}
	@Override
	public boolean deleteOrderinfo(String userid) {
		return false;
	}
	@Override
	public boolean updateOrderState(String oid, String state) {

		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set service_type=? where order_num=?")
			.setString(0,state)
			.setString(1,oid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Orderinfo selectByuserid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where userid=?").setString(0,userid);
		Orderinfo Order=(Orderinfo) query.uniqueResult();
		return Order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orderinfo> selectByid(String userid) {
		List<Orderinfo> list = this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where userid=?").setString(0, userid).list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> getPark(List<Orderinfo> list) {
		//拼装list
		StringBuffer userid=new StringBuffer();
		for (int i=0;i<list.size();i++) {
			userid.append("'"+list.get(i).getParkId()+"'");
			if(i<list.size()-1){
				userid.append(",");
			}
		}
		List<Park> plist=getSessionFactory().openSession().createQuery("from Park where id in ("+userid+")").list();
		return plist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Orderinfo> getOrder(String userid){
		List<Orderinfo> parks=getSessionFactory().openSession().createQuery("from Orderinfo where userId = '"+userid+"'").list();;
		return parks;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderrecord(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select p.id,p.name,p.address,o.park_start_time ,o.park_end_time from Park p ,Orderinfo o where p.id=o.parkId and o.userId=? order by order_request_time desc").setString(0,userid);
		List<Object> list  =query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> ordercode(String userid,int pageIndex,int pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select p.id,p.name,p.address,o.park_start_time ,o.park_end_time,o.paystatus,o.money,o.order_num,o.oderstate,o.thankcharge,o.sharemoney,o.security from Park p ,Orderinfo o where p.id=o.parkId and o.userId=? order by order_request_time desc").setString(0,userid);
		query.setFirstResult(pageIndex * pageSize);  
		query.setMaxResults(pageSize);
		List<Object> list =query.list();
		return list;
	}
	@Override
	public boolean updateOrder(String exchange_time, String pay_time,String pay_type, String service_type, String paystatus,String order_num) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set exchange_time=?,pay_time=?,pay_type=?,service_type=?,paystatus=? where order_num=?")
			.setString(0,exchange_time)
			.setString(1,pay_time)
			.setString(2,pay_type)
			.setString(3,service_type)
			.setString(4,paystatus)
			.setString(5,order_num)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderJpush(String out_trade_no) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select u.name,u.mobile,o.park_start_time,o.park_end_time,o.pay_time,o.plate_no,o.oderstate,o.order_num from User u ,Orderinfo o where u.id=o.userId and o.order_num=?").setString(0,out_trade_no);
		List<Object> list  =query.list();
		return list;
	}
	//查询停车场所有保安
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderisadminandstatus(String out_trade_no) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select u.id from User u where u.parkId in (select o.parkId from Orderinfo o where o.order_num=?)").setString(0,out_trade_no);
		List<Object> list  =query.list();
		return list;
	}
	//查询保安是否是还是保安，，是否为在线
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderstatus(String uid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select u.is_admin,u.status from User u where u.id=?").setString(0,uid);
		List<Object> list  =query.list();
		return list;
	}

	@Override
	public boolean updateOrderstate(String oderstate, String order_num) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set oderstate=? where order_num=?")
			.setString(0,oderstate)
			.setString(1,order_num)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Orderinfo selectOneByid(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where order_num=?").setString(0,order_num);
		Orderinfo Order=(Orderinfo) query.uniqueResult();
		return Order;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderpark(String parkid,int pageIndex, int pageSize){
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select u.name,u.mobile,o.park_start_time,o.park_end_time,o.pay_time,o.plate_no,o.oderstate,o.order_num from User u ,Orderinfo o where u.id=o.userId and paystatus=1 and o.parkId=? order by order_request_time desc").setString(0,parkid);
		query.setFirstResult(pageIndex * pageSize);  
		query.setMaxResults(pageSize);
		List<Object> list =query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> orderparks(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select parkId from Orderinfo where userId=?").setString(0,userid);
		List<Object> list  =query.list();
		return list;
	}

	@Override
	public boolean deleteOrderid(String id) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("delete from Orderinfo where id=?")
			.setString(0,id)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Orderinfo> selectuserid(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where order_num=?").setString(0,order_num);
		List<Orderinfo> list  =query.list();
		return list;
	}

	@Override
	public Orderinfo selectOneByuserid(String id) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo where id=? ").setString(0,id);
		Orderinfo order=(Orderinfo) query.uniqueResult();
		return order;
	}

	@Override
	public long getDuration(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select sum(time_to_sec(timediff(park_end_time, park_start_time))) from Orderinfo  where userId=? ").setString(0,userid);
		long duration = (long) query.uniqueResult();
		return duration;
	}

	@Override
	public long getTimes(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select COUNT(*) from Orderinfo where userId=?").setString(0,userid);
		long times = (long) query.uniqueResult();
		return times;
	}

	@Override
	public long getDuratio(String userid) {
		String sql="select sum(time_to_sec(timediff(park_end_time, park_start_time))) from order_info where userId=?";
		SQLQuery query=(SQLQuery) this.getSessionFactory().getCurrentSession().createSQLQuery(sql).iterate().next();
		query.setString(0,userid);
		long duration =(long)query.uniqueResult();
		return duration;
	}

	@Override
	public long getTime(String userid) {
		String sql="select COUNT(*) from order_info where userId=?";
		SQLQuery query=(SQLQuery) this.getSessionFactory().getCurrentSession().createSQLQuery(sql).iterate().next();
		query.setString(0,userid);
		long times =(long)query.uniqueResult();
		return times;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Orderinfo> selectAll(int pageIndex, int pageSize) {
		Pager<Orderinfo> pager=new Pager<Orderinfo>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<Orderinfo> list=this.getSessionFactory().getCurrentSession().createQuery("from Orderinfo").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from Orderinfo").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Object> selectByOrderid(String orderid) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery("select o.oderstate,o.sharemoney,o.money,o.thankcharge,o.park_start_time,o.park_end_time,p.name,p.entry_address "
						+ "from Orderinfo o, Park p where p.id=o.parkId and o.order_num = ?").setString(0,orderid);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public boolean updateOrderstarttime(String starttiming, String order_num) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set starttiming=? where order_num=?")
			.setString(0,starttiming)
			.setString(1,order_num)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateOrderenttime(String endtiming,String order_num) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update Orderinfo set endtiming=? where order_num=?")
			.setString(0,endtiming)
			.setString(1,order_num)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> GetOrderinfoRecord(String parkid, Integer pageIndex, Integer pageSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select u.name,u.mobile,o.park_start_time,o.park_end_time,o.pay_time,o.plate_no,o.oderstate,o.order_num,o.starttiming,o.endtiming from sysuser u,Orderinfo o where u.id=o.userId and paystatus=1 and o.parkId=? order by order_request_time desc").setString(0,parkid);
		query.setFirstResult(pageIndex * pageSize);  
		query.setMaxResults(pageSize);
		List<Object> list =query.list();
		return list;
	}

	@Override
	public parking ByParkingreservation(String parkid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from parking where id=?").setString(0,parkid);
		parking park = (parking) query.uniqueResult();
		return park;
	}

	@Override
	public Ordersecurity ByOrdersecuritysecurity(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Ordersecurity where order_num=?").setString(0,order_num);
		Ordersecurity order = (Ordersecurity) query.uniqueResult();
		return order;
	}

	@Override
	public Double getsubscription(String parkid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select subscription from parking where id=?").setString(0,parkid);
		Double subsc=(Double)query.uniqueResult();
		return subsc;
	}

	@Override
	public Long getOrderinfocount(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select count(*) FROM Ordersecurity where userid=? and oderstate='1' and starttiming is not null and paystatus='1'").setString(0,order_num);
		Long coun=(Long)query.uniqueResult();
		return coun;
	}

	@Override
	public String getOrderinfuserid(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userId from Ordersecurity where order_num=?").setString(0,order_num);
		String user=(String)query.uniqueResult();
		return user;
	}

	@Override
	public String getOrderinfstarttiming(String order_num) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select starttiming from Ordersecurity where order_num=?").setString(0,order_num);
		String starttiming=(String)query.uniqueResult();
		return starttiming;
	}

	@Override
	public String getuserinvideCode(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from sysuser where id=?").setString(0,userid);
		String mobile=(String)query.uniqueResult();
		return mobile;
	}

	@Override
	public String getyaoqinrenuserid(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select id from sysuser where mobile=(select startmoble from invitation where rtartmoble=?)").setString(0,mobile);
		String user=(String)query.uniqueResult();
		return user;
	}
}
