package com.xtc.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="drawcashInfo")
public class DrawcashInfo {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String drawid;
	@ManyToOne(cascade={CascadeType.ALL})           
    @JoinColumn(name="bankid")  
	private Bank bank;
	private Date endtime;
	private Double money;
	private String userid;
	private String state;
	private Date finaltime;
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getDrawid() {
		return drawid;
	}
	public void setDrawid(String drawid) {
		this.drawid = drawid;
	}

	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(Date finaltime) {
		this.finaltime = finaltime;
	}
	public DrawcashInfo(String drawid, Bank bank, Date endtime, Double money,
			String userid, String state, Date finaltime) {
		this.drawid = drawid;
		this.bank = bank;
		this.endtime = endtime;
		this.money = money;
		this.userid = userid;
		this.state = state;
		this.finaltime = finaltime;
	}
	public DrawcashInfo() {
	}
}
