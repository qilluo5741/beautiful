package com.xtc.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="drawcashInfo")
public class drawcash {
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
	@ManyToOne(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="userId")
	private User user;
	private String state;
	private Date finaltime;
	public Date getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(Date finaltime) {
		this.finaltime = finaltime;
	}
	public String getDrawid() {
		return drawid;
	}
	public void setDrawid(String drawid) {
		this.drawid = drawid;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public drawcash() {
	}
	public drawcash(String drawid, Bank bank, Date endtime, Double money,
			User user, String state) {
		this.drawid = drawid;
		this.bank = bank;
		this.endtime = endtime;
		this.money = money;
		this.user = user;
		this.state = state;
	} 
}
