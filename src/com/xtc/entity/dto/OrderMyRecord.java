package com.xtc.entity.dto;

public class OrderMyRecord {
	private String id;
	private String name;
	private String address;
	private String starttime;
	private String endtime;
	private String paystatus;//付款状态 支付状态(0:未支付，1：已支付,2:已取消)
	private double money;//预约费
	private String order_num;//订单号
	private String oderstate;
	private double thankcharge;
	private double sharemoney;
	private double security;
	public double getSharemoney() {
		return sharemoney;
	}
	public void setSharemoney(double sharemoney) {
		this.sharemoney = sharemoney;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getOderstate() {
		return oderstate;
	}
	public void setOderstate(String oderstate) {
		this.oderstate = oderstate;
	}
	public double getThankcharge() {
		return thankcharge;
	}
	public void setThankcharge(double thankcharge) {
		this.thankcharge = thankcharge;
	}
	public double getSecurity() {
		return security;
	}
	public void setSecurity(double security) {
		this.security = security;
	}
}
