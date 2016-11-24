package com.xtc.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xtc.dao.IUserDao;
import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.information;
import com.xtc.util.Pager;
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao{

	@Override
	public boolean updateuser(User user) {
		this.getSessionFactory().getCurrentSession().update(user);
		return true;
	}
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<User> selectAll() {
		return null;
	}

	@Override
	public User selectOneByuid(String uid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where id=?").setString(0,uid);
		User user=(User) query.uniqueResult();
		return user;
	}

	@Override
	public boolean insert(User user) {
		try {
			this.getSessionFactory().getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 根据手机号修改状态
	 */
	@Override
	public boolean updateUser(String mobile,String state) {

		try {
			this.getSessionFactory().getCurrentSession().createQuery("update User set status=? where mobile=?")
			.setString(0,state)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String uid) {
		return false;
	}

	@Override
	public List<User> selectByUserid(String userid) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectBymobil(String mobil) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where mobile=?").setString(0,mobil);
		List<User> list=query.list();
		return list;	
	}
	@Override
	public User selectOne(String invideCode) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where invideCode=?").setString(0,invideCode);
		User user = (User) query.uniqueResult(); 
		return user;
	}
	@Override
	public boolean updateInvideCode(String invideCode,String mobile) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set invideCode=? where mobile=?")
			.setString(0,invideCode)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getByparkid(String parkid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where parkId=?").setString(0,parkid);
		List<User> list= query.list(); 
		return list;
	}
	@Override
	public User selectBymobile(String mobile) {
		return (User) this.getSessionFactory().getCurrentSession().createQuery("from User where mobile=?").setString(0,mobile).uniqueResult();
	}
	@Override
	public boolean updateis_guard(String invideCode, String is_guard) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_guard=? where invideCode=?")
			.setString(0,is_guard)
			.setString(1,invideCode)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateParkid(String is_admin,String invidecode) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=?,parkId='' where invideCode=?")
			.setString(0,is_admin)
			.setString(1,invidecode)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateparkid(String is_admin,String parkid,String invideCode){
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=?,parkId=? where invideCode=?")
			.setString(0,is_admin)
			.setString(1,parkid)
			.setString(2,invideCode)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateis_admin(String invideCode) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=2 where invideCode=?")
			.setString(0,invideCode)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateadmin(String invideCode) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=0 where invideCode=?")
			.setString(0,invideCode)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public User getOne(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where mobile=?").setString(0,mobile);
		User u = (User) query.uniqueResult();
		return u;
	}
	@Override
	public User getInfo(String mobile, String password) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where mobile=? and password=?").setString(0,mobile).setString(1,password);
		User user = (User) query.uniqueResult();
		return user;
	}
	@Override
	public User mobile(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where mobile=?").setString(0,mobile);
		User user = (User) query.uniqueResult();
		return user;
	}
	@Override
	public boolean updateauth_code(String auth_code, String mobile) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set auth_code=? where mobile=?")
			.setString(0,auth_code)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public User mobileandpassword(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from User where mobile= ? and password is null").setString(0,mobile);
		User user = (User) query.uniqueResult();
		return user;
	}
	@Override
	public boolean updateregister(String password,String mobile) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set password=? where mobile=?")
			.setString(0,password)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateavatar(String avatar, String mobile) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set avatar=? where mobile=?")
			.setString(0,avatar)
			.setString(1,mobile)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<User> selectAll(int pageIndex, int pageSize) {
		Pager<User> pager=new Pager<User>();
		//设置当前页数
		pager.setPageIndex(pageIndex);
		//设置每页的大小
		pager.setPageSize(pageSize);
		List<User> list=this.getSessionFactory().getCurrentSession().createQuery("from User").setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		pager.setList(list);
		pager.setTotalRecords(((Long)this.getSessionFactory().getCurrentSession().createQuery("select count(*) from User").uniqueResult()).intValue());
		pager.setTotalPages();
		return pager;
	}
	@Override
	public boolean updateState(String id) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=1 where id=?")
			.setString(0,id)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updatesex(String id) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set sex='女' where id=?")
			.setString(0,id)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateStates(String id) {
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update User set is_admin=0 where id=?")
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
	public List<Object> RegistCount(Integer year,Integer month){
		Query query = this.getSessionFactory().getCurrentSession()
		.createSQLQuery("select DATE_FORMAT(date_created,\"%Y-%m-%d\") dateupdate,count(*) count from  sys_user where date_created > ? and date_created<? group by DATE_FORMAT(date_created,\"%Y-%m-%d\")")
		.setString(0,year+"-"+(month<10?"0"+month:month))
		.setString(1,year+"-"+((month+1)<10?"0"+(month+1):(month+1)));
		List<Object> list =query.list();
		return list;
	}
	@Override
	public String Bymobilenot(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from User where mobile=?").setString(0,mobile);
		String user=(String)query.uniqueResult();
		return user;
	}
	@Override
	public String selectis_guardBymobile(String mobile) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select invideCode from User where mobile=?").setString(0,mobile);
		String guard=(String)query.uniqueResult();
		return guard;
	}
	@Override
	public String Selectmobile(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select mobile from User where id=?").setString(0,userid);
		String user=(String)query.uniqueResult();
		return user;
	}
	@Override
	public boolean addVehicleLicense(VehicleLicense vehicle) {
		try {
			this.getSessionFactory().getCurrentSession().save(vehicle);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean addinformation(information mation) {
		try {
			this.getSessionFactory().getCurrentSession().save(mation);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateinformation(long duration,int jl,long times,String userid){
		try {
			this.getSessionFactory()
			.getCurrentSession()
			.createQuery("update information set duration=?,jl=?,times=? where userid=?")
			.setLong(0,duration)
			.setInteger(1,jl)
			.setLong(2,times)
			.setString(3,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String selectinformation(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("select userid from information where userid=?").setString(0,userid);
		String user=(String)query.uniqueResult();
		return user;
	}
	@Override
	public information getinformation(String userid) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from information where userid=?").setString(0,userid);
		information mation = (information) query.uniqueResult();
		return mation;
	}
	@Override
	public boolean deleteinformation(String userid) {
		try {
			getSessionFactory().getCurrentSession().createQuery("delete from information where userid= ?")
			.setString(0,userid)
			.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
