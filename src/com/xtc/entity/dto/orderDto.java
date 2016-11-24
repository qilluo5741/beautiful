package com.xtc.entity.dto;

public class orderDto {
	private String oderstate;
	private double sharemoney;
	private double money;
	private double thankcharge;
	private String endtime;
	private String starttime;
	private String name;
	private String address;
	public String getOderstate() {
		return oderstate;
	}
	public void setOderstate(String oderstate) {
		this.oderstate = oderstate;
	}
	public double getSharemoney() {
		return sharemoney;
	}
	public void setSharemoney(double sharemoney) {
		this.sharemoney = sharemoney;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getThankcharge() {
		return thankcharge;
	}
	public void setThankcharge(double thankcharge) {
		this.thankcharge = thankcharge;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
	public orderDto(String oderstate, double sharemoney, double money,
			double thankcharge, String endtime, String starttime, String name,
			String address) {
		super();
		this.oderstate = oderstate;
		this.sharemoney = sharemoney;
		this.money = money;
		this.thankcharge = thankcharge;
		this.endtime = endtime;
		this.starttime = starttime;
		this.name = name;
		this.address = address;
	}
	public orderDto() {
	}
	
}