package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="orderpark")
public class orderpark {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String  pinId;
	private String  sename;
	private String  usname;
	private String  evaluate;
	private String  evaluatr;
	private String  ordertime;
	private String  starttime;
	private String  endtime;
	public String getPinId() {
		return pinId;
	}
	public void setPinId(String pinId) {
		this.pinId = pinId;
	}
	public String getSename() {
		return sename;
	}
	public void setSename(String sename) {
		this.sename = sename;
	}
	public String getUsname() {
		return usname;
	}
	public void setUsname(String usname) {
		this.usname = usname;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getEvaluatr() {
		return evaluatr;
	}
	public void setEvaluatr(String evaluatr) {
		this.evaluatr = evaluatr;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
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
	public orderpark() {
	}
	public orderpark(String pinId, String sename, String usname,
			String evaluate, String evaluatr, String ordertime,
			String starttime, String endtime) {
		this.pinId = pinId;
		this.sename = sename;
		this.usname = usname;
		this.evaluate = evaluate;
		this.evaluatr = evaluatr;
		this.ordertime = ordertime;
		this.starttime = starttime;
		this.endtime = endtime;
	}
}
