package com.xtc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="increorder")
public class increorder {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String inorderid;
	private String inorderNo;//'订单号',
	private String goodproperid;//
	private String repmenuid;//
	private String commodityid;
	private String inordertime;//'订单时间',
	private Date usagetime;//'使用时间',
	private int inorderstatus;//'使用状态(0:未使用，1：已使用)',
	private double inordermoney;
	private String instatus;//'支付状态(0:未支付 1:支付宝，2：微信)',
	private Date intime;//'支付时间',
	private int inordcount;
	private String inordAddress;//'收货地址',
	private String inordphone;//'联系人',
	private String inordUname;// '联系名字',
	private String userid;//'用户id',
	private int isdelete;
	public String getInorderid() {
		return inorderid;
	}
	public void setInorderid(String inorderid) {
		this.inorderid = inorderid;
	}
	public String getInorderNo() {
		return inorderNo;
	}
	public void setInorderNo(String inorderNo) {
		this.inorderNo = inorderNo;
	}
	public String getGoodproperid() {
		return goodproperid;
	}
	public void setGoodproperid(String goodproperid) {
		this.goodproperid = goodproperid;
	}
	public String getRepmenuid() {
		return repmenuid;
	}
	public void setRepmenuid(String repmenuid) {
		this.repmenuid = repmenuid;
	}
	public String getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(String commodityid) {
		this.commodityid = commodityid;
	}
	public String getInordertime() {
		return inordertime;
	}
	public void setInordertime(String inordertime) {
		this.inordertime = inordertime;
	}
	public Date getUsagetime() {
		return usagetime;
	}
	public void setUsagetime(Date usagetime) {
		this.usagetime = usagetime;
	}
	public int getInorderstatus() {
		return inorderstatus;
	}
	public void setInorderstatus(int inorderstatus) {
		this.inorderstatus = inorderstatus;
	}
	public double getInordermoney() {
		return inordermoney;
	}
	public void setInordermoney(double inordermoney) {
		this.inordermoney = inordermoney;
	}
	public String getInstatus() {
		return instatus;
	}
	public void setInstatus(String instatus) {
		this.instatus = instatus;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public int getInordcount() {
		return inordcount;
	}
	public void setInordcount(int inordcount) {
		this.inordcount = inordcount;
	}
	public String getInordAddress() {
		return inordAddress;
	}
	public void setInordAddress(String inordAddress) {
		this.inordAddress = inordAddress;
	}
	public String getInordphone() {
		return inordphone;
	}
	public void setInordphone(String inordphone) {
		this.inordphone = inordphone;
	}
	public String getInordUname() {
		return inordUname;
	}
	public void setInordUname(String inordUname) {
		this.inordUname = inordUname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public increorder() {
	}
	public increorder(String inorderid, String inorderNo, String goodproperid, String repmenuid, String commodityid,
			String inordertime, Date usagetime, int inorderstatus, double inordermoney, String instatus, Date intime,
			int inordcount, String inordAddress, String inordphone, String inordUname, String userid, int isdelete) {
		this.inorderid = inorderid;
		this.inorderNo = inorderNo;
		this.goodproperid = goodproperid;
		this.repmenuid = repmenuid;
		this.commodityid = commodityid;
		this.inordertime = inordertime;
		this.usagetime = usagetime;
		this.inorderstatus = inorderstatus;
		this.inordermoney = inordermoney;
		this.instatus = instatus;
		this.intime = intime;
		this.inordcount = inordcount;
		this.inordAddress = inordAddress;
		this.inordphone = inordphone;
		this.inordUname = inordUname;
		this.userid = userid;
		this.isdelete = isdelete;
	}
}
