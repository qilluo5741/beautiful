package com.xtc.entity.dto;

import java.util.Date;


public class drawinfoDto {
	private Date endtime;
	private double money;
	private String name;
	private String mobile;
	private String state;
	private Date finaltime;
	public Date getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(Date finaltime) {
		this.finaltime = finaltime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
}
