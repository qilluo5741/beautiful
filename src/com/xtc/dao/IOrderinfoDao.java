package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.Park;
import com.xtc.entity.parking;
import com.xtc.util.Pager;
public interface IOrderinfoDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Orderinfo> selectAll();
	/**
	 * ������ѯ
	 * @return
	 */
	public Orderinfo selectOneByoid(String oid);
	public Orderinfo selectOneByuserid(String id);
	/**
	 * ��������
	 * @return
	 */
	public boolean insert(Orderinfo orderinfo);
	/**
	 * �޸�����
	 * @return
	 */
	public boolean updateOrderinfo( );
	/**
	 * ɾ������
	 * @return
	 */
	public boolean deleteOrderinfo(String userid);
	/**
	 * ���Ķ�����״̬
	 */
	public boolean updateOrderState(String oid,String state);
	/**
	 * ֧����֧���ɹ���״̬
	 * @param exchange_time
	 * @param pay_time
	 * @param pay_type
	 * @param service_type
	 * @param paystatus
	 * @param order_num
	 * @return
	 */
	public boolean updateOrder(String exchange_time,String pay_time,String pay_type,String service_type,String paystatus,String order_num);
	/**
	 * ��ѯ�������
	 */
	public Orderinfo selectByuserid(String userid);
	
	public List<Orderinfo> selectByid(String userid);
	
	public List<Park>  getPark(List<Orderinfo> list);
	public List<Orderinfo> getOrder(String userid);
	//ԤԼ��¼
	public  List<Object> orderrecord(String userid);
	public List<Object> ordercode(String userid,int pageIndex, int pageSize);
	public List<Object> orderJpush(String out_trade_no);
	public List<Object> orderisadminandstatus(String out_trade_no);
	public List<Object> orderstatus(String uid);
	public boolean updateOrderstate(String oderstate,String order_num);
	public Orderinfo selectOneByid(String order_num);
	public List<Object> orderpark(String parkid,int pageIndex, int pageSize);
	public List<Object> orderparks(String userid);
	
	public boolean deleteOrderid(String id);
	
	public  List<Orderinfo> selectuserid(String order_num);
	
	/**
	 * ��ѯԤԼʱ��
	 */
	 public long getDuration(String userid);
	  
	/**
	 * ��ѯԤԼ����
	 */
	 public long getTimes(String userid);
	 public long getDuratio(String userid);
	 public long getTime(String userid);
	 
	 public Pager<Orderinfo> selectAll(int pageIndex, int pageSize);
	 
	 /**
	  * ���ݶ����Ų�ѯ��������
	  */
	 public List<Object> selectByOrderid(String orderid);
	 
	 public boolean updateOrderstarttime(String starttiming,String order_num);
	 public boolean updateOrderenttime(String endtiming,String order_num);
	 /**
	  * ��������ԤԼ����
	  * @param parkid
	  * @param pageIndex
	  * @param pageSize
	  * @return
	  */
	 public List<Object> GetOrderinfoRecord(String parkid,Integer pageIndex,Integer pageSize);
	 public parking ByParkingreservation(String parkid);
	 public Ordersecurity ByOrdersecuritysecurity(String order_num);
		
	 public Double getsubscription(String parkid);
	 public String getOrderinfuserid(String order_num);
	 public Long getOrderinfocount(String order_num);
	 
	 public String getOrderinfstarttiming(String order_num);
	 public String getuserinvideCode(String userid);
	 public String getyaoqinrenuserid(String mobile);
}
