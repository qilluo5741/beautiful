package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="redshare")
public class redshare {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String shareid;
	private String sharemobile;
	private double sharemoney;
	private String sharecreate;//'开始时间',
	private String shareend;// '有效时间',
	private String mobile;//手机号码
	private String sharedate;
	private int sharedel;//'0是默认可以使用 1已经使用或删除'
	public String getShareid() {
		return shareid;
	}
	public void setShareid(String shareid) {
		this.shareid = shareid;
	}
	public String getSharemobile() {
		return sharemobile;
	}
	public void setSharemobile(String sharemobile) {
		this.sharemobile = sharemobile;
	}
	public double getSharemoney() {
		return sharemoney;
	}
	public void setSharemoney(double sharemoney) {
		this.sharemoney = sharemoney;
	}
	public String getSharecreate() {
		return sharecreate;
	}
	public void setSharecreate(String sharecreate) {
		this.sharecreate = sharecreate;
	}
	public String getShareend() {
		return shareend;
	}
	public void setShareend(String shareend) {
		this.shareend = shareend;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getSharedel() {
		return sharedel;
	}
	public void setSharedel(int sharedel) {
		this.sharedel = sharedel;
	}
	public String getSharedate() {
		return sharedate;
	}
	public void setSharedate(String sharedate) {
		this.sharedate = sharedate;
	}
	public redshare() {}
	public redshare(String shareid, String sharemobile, double sharemoney, String sharecreate, String shareend,
			String mobile, String sharedate, int sharedel) {
		this.shareid = shareid;
		this.sharemobile = sharemobile;
		this.sharemoney = sharemoney;
		this.sharecreate = sharecreate;
		this.shareend = shareend;
		this.mobile = mobile;
		this.sharedate = sharedate;
		this.sharedel = sharedel;
	}
}
