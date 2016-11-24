package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IOrderinfoDao;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.Park;
import com.xtc.entity.parking;
import com.xtc.service.IOrderinfoService;
import com.xtc.util.Pager;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OrderSreviceImpl implements IOrderinfoService{
	@Autowired
	private IOrderinfoDao dao;
	@Override
	public List<Orderinfo> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Orderinfo selectOneByoid(String oid) {
		return dao.selectOneByoid(oid);
	}

	@Override
	public boolean insert(Orderinfo orderinfo) {
		return dao.insert(orderinfo);
	}

	@Override
	public boolean updateOrderinfo() {
		return dao.updateOrderinfo();
	}

	@Override
	public boolean deleteOrderinfo(String userid) {
		return dao.deleteOrderinfo(userid);
	}

	@Override
	public boolean updateOrderState(String oid, String state) {
		return dao.updateOrderState(oid, state);
	}

	@Override
	public Orderinfo selectByuserid(String userid) {
		return dao.selectByuserid(userid);
	}

	@Override
	public List<Orderinfo> selectByid(String userid) {
		return dao.selectByid(userid);
	}

	@Override
	public List<Park> getPark(List<Orderinfo> list) {
		return dao.getPark(list);
	}

	@Override
	public List<Orderinfo> getOrder(String userid) {
		return dao.getOrder(userid);
	}

	@Override
	public List<Object> orderrecord(String userid) {
		return dao.orderrecord(userid);
	}

	@Override
	public boolean updateOrder(String exchange_time, String pay_time,
			String pay_type, String service_type, String paystatus,
			String order_num) {
		return dao.updateOrder(exchange_time, pay_time, pay_type, service_type, paystatus, order_num);
	}

	@Override
	public List<Object> ordercode(String userid,int pageIndex, int pageSize) {
		return dao.ordercode(userid, pageIndex, pageSize);
	}

	@Override
	public List<Object> orderJpush(String out_trade_no) {
		return dao.orderJpush(out_trade_no);
	}

	@Override
	public List<Object> orderisadminandstatus(String out_trade_no) {
		return dao.orderisadminandstatus(out_trade_no);
	}

	@Override
	public List<Object> orderstatus(String uid) {
		return dao.orderstatus(uid);
	}

	@Override
	public boolean updateOrderstate(String oderstate, String order_num) {
		return dao.updateOrderstate(oderstate, order_num);
	}

	@Override
	public Orderinfo selectOneByid(String order_num) {
		return dao.selectOneByid(order_num);
	}

	/*@Override
	public Pager<Object> orderpark(String parkid,int pageIndex, int pageSize) {
		return dao.orderpark(parkid, pageSize, pageSize);
	}*/

	@Override
	public List<Object> orderparks(String userid) {
		return dao.orderparks(userid);
	}

	@Override
	public List<Object> orderpark(String parkid,int pageIndex,int pageSize) {
		return dao.orderpark(parkid, pageIndex, pageSize);
	}

	@Override
	public boolean deleteOrderid(String id) {
		return dao.deleteOrderid(id);
	}

	@Override
	public  List<Orderinfo> selectuserid(String order_num) {
		return dao.selectuserid(order_num);
	}

	@Override
	public Orderinfo selectOneByuserid(String id) {
		return dao.selectOneByuserid(id);
	}

	@Override
	public long getDuration(String userid) {
		return dao.getDuration(userid);
	}

	@Override
	public long getTimes(String userid) {
		return dao.getTimes(userid);
	}

	@Override
	public long getDuratio(String userid) {
		return dao.getDuratio(userid);
	}

	@Override
	public long getTime(String userid) {
		return dao.getTime(userid);
	}

	@Override
	public Pager<Orderinfo> selectAll(int pageIndex, int pageSize) {
		return dao.selectAll(pageIndex, pageSize);
	}

	@Override
	public List<Object> selectByOrderid(String orderid) {
		return dao.selectByOrderid(orderid);
	}

	@Override
	public boolean updateOrderstarttime(String starttiming, String order_num) {
		return dao.updateOrderstarttime(starttiming, order_num);
	}

	@Override
	public boolean updateOrderenttime(String endtiming, String order_num) {
		return dao.updateOrderenttime(endtiming, order_num);
	}

	@Override
	public List<Object> GetOrderinfoRecord(String parkid, Integer pageIndex, Integer pageSize) {
		return dao.GetOrderinfoRecord(parkid, pageIndex, pageSize);
	}

	@Override
	public parking ByParkingreservation(String parkid) {
		return dao.ByParkingreservation(parkid);
	}

	@Override
	public Ordersecurity ByOrdersecuritysecurity(String order_num) {
		return dao.ByOrdersecuritysecurity(order_num);
	}

	@Override
	public Double getsubscription(String parkid) {
		return dao.getsubscription(parkid);
	}

	@Override
	public Long getOrderinfocount(String order_num) {
		return dao.getOrderinfocount(order_num);
	}

	@Override
	public String getOrderinfuserid(String order_num) {
		return dao.getOrderinfuserid(order_num);
	}

	@Override
	public String getOrderinfstarttiming(String order_num) {
		return dao.getOrderinfstarttiming(order_num);
	}

	@Override
	public String getuserinvideCode(String userid) {
		return dao.getuserinvideCode(userid);
	}

	@Override
	public String getyaoqinrenuserid(String mobile) {
		return dao.getyaoqinrenuserid(mobile);
	}
}
