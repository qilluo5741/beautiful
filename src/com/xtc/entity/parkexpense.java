package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="parkexpense")
public class parkexpense{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String parkdeid;
	private String licenseplate;
	private double expense;
	private double receivable;
	private String chargestatus;//收费状态 0 未收费 1 已收费 2 物业车辆 3 军用车辆 4 其他类型',
	private String parkid;
	private String tollRremname;
	private String userid;
	private String firsttime;
	private String factorytime;
	private String paymentstatus;
	private String ordernumber;
	private int issenior;
	private String sysuserid;
	public String getParkdeid() {
		return parkdeid;
	}
	public void setParkdeid(String parkdeid) {
		this.parkdeid = parkdeid;
	}
	public String getLicenseplate() {
		return licenseplate;
	}
	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public double getReceivable() {
		return receivable;
	}
	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}
	public String getChargestatus() {
		return chargestatus;
	}
	public void setChargestatus(String chargestatus) {
		this.chargestatus = chargestatus;
	}
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public String getTollRremname() {
		return tollRremname;
	}
	public void setTollRremname(String tollRremname) {
		this.tollRremname = tollRremname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	public String getFactorytime() {
		return factorytime;
	}
	public void setFactorytime(String factorytime) {
		this.factorytime = factorytime;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public int getIssenior() {
		return issenior;
	}
	public void setIssenior(int issenior) {
		this.issenior = issenior;
	}
	public String getSysuserid() {
		return sysuserid;
	}
	public void setSysuserid(String sysuserid) {
		this.sysuserid = sysuserid;
	}
	public parkexpense() {
	}
	public parkexpense(String parkdeid, String licenseplate, double expense, double receivable, String chargestatus,
			String parkid, String tollRremname, String userid, String firsttime, String factorytime,
			String paymentstatus, String ordernumber, int issenior, String sysuserid) {
		this.parkdeid = parkdeid;
		this.licenseplate = licenseplate;
		this.expense = expense;
		this.receivable = receivable;
		this.chargestatus = chargestatus;
		this.parkid = parkid;
		this.tollRremname = tollRremname;
		this.userid = userid;
		this.firsttime = firsttime;
		this.factorytime = factorytime;
		this.paymentstatus = paymentstatus;
		this.ordernumber = ordernumber;
		this.issenior = issenior;
		this.sysuserid = sysuserid;
	}
}
