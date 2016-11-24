package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="bonusshare")
public class bonusshare {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String bonusid;
	private String bonusmobile;
	private int bonuscount;
	private String bonusdate;
	private String order_num;
	public String getBonusid() {
		return bonusid;
	}
	public void setBonusid(String bonusid) {
		this.bonusid = bonusid;
	}
	public String getBonusmobile() {
		return bonusmobile;
	}
	public void setBonusmobile(String bonusmobile) {
		this.bonusmobile = bonusmobile;
	}
	public int getBonuscount() {
		return bonuscount;
	}
	public void setBonuscount(int bonuscount) {
		this.bonuscount = bonuscount;
	}
	public String getBonusdate() {
		return bonusdate;
	}
	public void setBonusdate(String bonusdate) {
		this.bonusdate = bonusdate;
	}
	
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public bonusshare() {
	}
	public bonusshare(String bonusid, String bonusmobile, int bonuscount, String bonusdate, String order_num) {
		this.bonusid = bonusid;
		this.bonusmobile = bonusmobile;
		this.bonuscount = bonuscount;
		this.bonusdate = bonusdate;
		this.order_num = order_num;
	}
}
