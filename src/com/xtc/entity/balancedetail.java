package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="balancedetail")
public class balancedetail {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String badeid;//varchar(36) primary key not null,
	private String userid;// varchar(36) NOT NULL,
	private String	starttime;// timestamp NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '
	private String	balancetype;// varchar(36) NOT NULL,余额明细状态 0 支付宝，1微信，2余额，3保安收益，4退款',5,保安关联收益
	private double 	money;// double not null
	public String getBadeid() {
		return badeid;
	}
	public void setBadeid(String badeid) {
		this.badeid = badeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getBalancetype() {
		return balancetype;
	}
	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public balancedetail() {
	}
	public balancedetail(String badeid, String userid, String starttime, String balancetype, double money) {
		this.badeid = badeid;
		this.userid = userid;
		this.starttime = starttime;
		this.balancetype = balancetype;
		this.money = money;
	}
}
