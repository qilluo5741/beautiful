package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.Park;
import com.xtc.entity.parking;
import com.xtc.util.Pager;
public interface IOrderinfoDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Orderinfo> selectAll();
	/**
	 * 单个查询
	 * @return
	 */
	public Orderinfo selectOneByoid(String oid);
	public Orderinfo selectOneByuserid(String id);
	/**
	 * 插入数据
	 * @return
	 */
	public boolean insert(Orderinfo orderinfo);
	/**
	 * 修改数据
	 * @return
	 */
	public boolean updateOrderinfo( );
	/**
	 * 删除数据
	 * @return
	 */
	public boolean deleteOrderinfo(String userid);
	/**
	 * 更改订单的状态
	 */
	public boolean updateOrderState(String oid,String state);
	/**
	 * 支付宝支付成功后状态
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
	 * 查询订单编号
	 */
	public Orderinfo selectByuserid(String userid);
	
	public List<Orderinfo> selectByid(String userid);
	
	public List<Park>  getPark(List<Orderinfo> list);
	public List<Orderinfo> getOrder(String userid);
	//预约记录
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
	 * 查询预约时长
	 */
	 public long getDuration(String userid);
	  
	/**
	 * 查询预约次数
	 */
	 public long getTimes(String userid);
	 public long getDuratio(String userid);
	 public long getTime(String userid);
	 
	 public Pager<Orderinfo> selectAll(int pageIndex, int pageSize);
	 
	 /**
	  * 根据订单号查询订单详情
	  */
	 public List<Object> selectByOrderid(String orderid);
	 
	 public boolean updateOrderstarttime(String starttiming,String order_num);
	 public boolean updateOrderenttime(String endtiming,String order_num);
	 /**
	  * （保安）预约订单
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
