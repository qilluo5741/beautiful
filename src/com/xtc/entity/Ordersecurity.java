package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="order_info")
public class Ordersecurity {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String  id;
	private String  vehicleId;
	private String  userId;
	private String  parkId;
	private String  park_start_time;
	private String  park_end_time;
	private String  exchange_time;		
	private String  order_request_time;				
	private String  order_response_time	;				
	private String  daibo_request_time;			
	private String  daibo_response_time;	
	private String  daibo_confirm_time;		
	private String  pay_time;
	private String  pay_type;
	private String  prepay_time	;		
	private String  service_date;	
	private String  service_type;
	private String  order_num;
	private double money;
	private String  plate_no;
	private String  oderstate;
	private String  paystatus;
	private double thankcharge;
	private double sharemoney;
	private String  starttiming;
	private String  endtiming;
	private double security;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getPark_start_time() {
		return park_start_time;
	}
	public void setPark_start_time(String park_start_time) {
		this.park_start_time = park_start_time;
	}
	public String getPark_end_time() {
		return park_end_time;
	}
	public void setPark_end_time(String park_end_time) {
		this.park_end_time = park_end_time;
	}
	public String getExchange_time() {
		return exchange_time;
	}
	public void setExchange_time(String exchange_time) {
		this.exchange_time = exchange_time;
	}
	public String getOrder_request_time() {
		return order_request_time;
	}
	public void setOrder_request_time(String order_request_time) {
		this.order_request_time = order_request_time;
	}
	public String getOrder_response_time() {
		return order_response_time;
	}
	public void setOrder_response_time(String order_response_time) {
		this.order_response_time = order_response_time;
	}
	public String getDaibo_request_time() {
		return daibo_request_time;
	}
	public void setDaibo_request_time(String daibo_request_time) {
		this.daibo_request_time = daibo_request_time;
	}
	public String getDaibo_response_time() {
		return daibo_response_time;
	}
	public void setDaibo_response_time(String daibo_response_time) {
		this.daibo_response_time = daibo_response_time;
	}
	public String getDaibo_confirm_time() {
		return daibo_confirm_time;
	}
	public void setDaibo_confirm_time(String daibo_confirm_time) {
		this.daibo_confirm_time = daibo_confirm_time;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPrepay_time() {
		return prepay_time;
	}
	public void setPrepay_time(String prepay_time) {
		this.prepay_time = prepay_time;
	}
	public String getService_date() {
		return service_date;
	}
	public void setService_date(String service_date) {
		this.service_date = service_date;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getPlate_no() {
		return plate_no;
	}
	public void setPlate_no(String plate_no) {
		this.plate_no = plate_no;
	}
	public String getOderstate() {
		return oderstate;
	}
	public void setOderstate(String oderstate) {
		this.oderstate = oderstate;
	}
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	public double getThankcharge() {
		return thankcharge;
	}
	public void setThankcharge(double thankcharge) {
		this.thankcharge = thankcharge;
	}
	public double getSharemoney() {
		return sharemoney;
	}
	public void setSharemoney(double sharemoney) {
		this.sharemoney = sharemoney;
	}
	public String getStarttiming() {
		return starttiming;
	}
	public void setStarttiming(String starttiming) {
		this.starttiming = starttiming;
	}
	public String getEndtiming() {
		return endtiming;
	}
	public void setEndtiming(String endtiming) {
		this.endtiming = endtiming;
	}
	public double getSecurity() {
		return security;
	}
	public void setSecurity(double security) {
		this.security = security;
	}
	public Ordersecurity() {
		super();
	}
	public Ordersecurity(String id, String vehicleId, String userId, String parkId, String park_start_time,
			String park_end_time, String exchange_time, String order_request_time, String order_response_time,
			String daibo_request_time, String daibo_response_time, String daibo_confirm_time, String pay_time,
			String pay_type, String prepay_time, String service_date, String service_type, String order_num,
			double money, String plate_no, String oderstate, String paystatus, double thankcharge, double sharemoney,
			String starttiming, String endtiming, double security) {
		super();
		this.id = id;
		this.vehicleId = vehicleId;
		this.userId = userId;
		this.parkId = parkId;
		this.park_start_time = park_start_time;
		this.park_end_time = park_end_time;
		this.exchange_time = exchange_time;
		this.order_request_time = order_request_time;
		this.order_response_time = order_response_time;
		this.daibo_request_time = daibo_request_time;
		this.daibo_response_time = daibo_response_time;
		this.daibo_confirm_time = daibo_confirm_time;
		this.pay_time = pay_time;
		this.pay_type = pay_type;
		this.prepay_time = prepay_time;
		this.service_date = service_date;
		this.service_type = service_type;
		this.order_num = order_num;
		this.money = money;
		this.plate_no = plate_no;
		this.oderstate = oderstate;
		this.paystatus = paystatus;
		this.thankcharge = thankcharge;
		this.sharemoney = sharemoney;
		this.starttiming = starttiming;
		this.endtiming = endtiming;
		this.security = security;
	}
	
}
