package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.xtc.dao.IparkDao;
import com.xtc.entity.Park;
import com.xtc.util.Pager;

@Repository
public class parkDaoImpl extends HibernateDaoSupport implements IparkDao {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean insert(Park park) {
		try {
			this.getSessionFactory().getCurrentSession().save(park);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateparks(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name,String cost) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set city=?,entry_address=?,address=?,capacity=?,carnum=?,type=?,price=?,cost=?,is_cooperate=? where name=?")
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
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updatepark(String city,String entry_address,String address,String capacity,String carnum,String type,String price,String is_cooperate,String name) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set city=?,entry_address=?,address=?,capacity=?,carnum=?,type=?,price=?,is_cooperate=? where name=?")
			.setString(0,city)
			.setString(1,entry_address)
			.setString(2,address)
			.setString(3,capacity)
			.setString(4,carnum)
			.setString(5,type)
			.setString(6,price)
			.setString(7,is_cooperate)
			.setString(8,name)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> selectAll() {
		/*List<Park> list = this.getSessionFactory().getCurrentSession().createQuery("from Park where moralid is null").list();
		return list;*/
		List<Park> list = this.getSessionFactory().getCurrentSession().createQuery("from Park").list();
		return list;
	}
	@Override
	public Park selectOneBysyspaid(String id) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from park p where p.id=? ").setString(0,id);
		Park park=(Park) query.uniqueResult();
		return park;
	}

	@Override
	public boolean deletepark(String id) {
		return false;
	}
	@Override 
	public Park selectByparkid(String userid) {
		Query query = this.getSessionFactory().openSession().createQuery("from Park where userid=?").setString(0,userid);
		Park park=(Park) query.uniqueResult();
		return park;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> selectByName(String name) {
		List<Park> list = this.getSessionFactory().getCurrentSession().createQuery("from Park where name like '%"+name+"%' or address like '%"+name+"%'").list();
		return list;
	}
	@Override
	public boolean updateState(String parkid, String state) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set status=? where id=?")
			.setString(0,state)
			.setString(1,parkid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Park selectByOneName(String name) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Park where name=?").setString(0,name);
		Park park=(Park) query.uniqueResult();
		return park;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectObject() {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select id,name,address,cost,status,userid from Park");
		List<Object> list = query.list();
		return list;
	}
	@Override
	public Park selectByOneId(String id) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Park where id=?").setString(0, id);
		Park p = (Park) query.uniqueResult();
		return p;
	}
	@Override
	public boolean updateuserid(String parkname, String userid) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set userid=? where name=?")
			.setString(0,userid)
			.setString(1,parkname)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectPark(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from Park where userid=?").setString(0,userid);
		List<Object> list  =query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Park> selectBystrs(String id) {
		List<Park> list = this.getSessionFactory().getCurrentSession().createQuery("from Park where id=?").setString(0,id).list();
		return list;
	}
	@Override
	public String selectParkid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userid from Park where userid=?").setString(0,userid);
		String park=(String)query.uniqueResult();
		return park;
	}
	@Override
	public String selectuserid(String name) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userid from Park where name=?").setString(0,name);
		String park=(String)query.uniqueResult();
		return park;
	}
	@Override
	public boolean updatecost(String cost, String name) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set cost=? where name=?")
			.setString(0,cost)
			.setString(1,name)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> RegistCount(Integer year,Integer month) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery("select DATE_FORMAT(start_time,\"%Y-%m-%d\") dateupdate,count(*) count from  sys_park where start_time > ? and start_time<? group by DATE_FORMAT(start_time,\"%Y-%m-%d\")")
				.setString(0,year+"-"+(month<10?"0"+month:month))
				.setString(1,year+"-"+((month+1)<10?"0"+(month+1):(month+1)));
				List<Object> list =query.list();
				return list;
	}
	@Override
	public String selectinvideCode(String relationCode) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select invideCode from relation where relationCode=?").setString(0,relationCode);
		String order=(String)query.uniqueResult();
		return order;
	}
	@Override
	public String selectbyuserid(String invideCode) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select id from User where invideCode=?").setString(0,invideCode);
		String order=(String)query.uniqueResult();
		return order;
	}
	@Override
	public String selectnotbalancedetail(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userid from balancedetail where balancetype='6' and money='10' and userid=?").setString(0,userid);
		String order=(String)query.uniqueResult();
		return order;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Park> selectAll(int pageIndex, int pageSize) {
		Pager<Park> pager=new Pager<Park>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<Park> list=this.getSessionFactory().getCurrentSession().createQuery("from Park").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from Park").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
	@Override
	public boolean updatemoralid(String moralid,String parkid) {
		try {
			this.getSessionFactory().getCurrentSession()
			.createQuery("update Park set moralid=? where id=?")
			.setString(0,moralid)
			.setString(1,parkid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String selectid(String name) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select id from Park where name=?").setString(0,name);
		String id=(String)query.uniqueResult();
		return id;
	}
	@Override
	public String selectmoralid(String name) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select moralid from Park where name=?").setString(0,name);
		String moralid=(String)query.uniqueResult();
		return moralid;
	}
	@Override
	public String selectgaodemoralid(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select moralid from Park where userid=?").setString(0,userid);
		String moralid=(String)query.uniqueResult();
		return moralid;
	}
	@Override
	public String selectprice(String parkid){
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select price from Park where id=?").setString(0,parkid);
		String price=(String)query.uniqueResult();
		return price;
	} 
}
