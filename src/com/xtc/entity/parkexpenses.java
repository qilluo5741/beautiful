package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="parkexpenses")
public class parkexpenses{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String parkexid;
	private String name;//停车场名字
	private String address;//停车场地址
	private String price;//价格
	private String licenseplate;//停车车牌号
	private String firsttime;//进场时间
	private String factorytime;//出场时间
	private String mobile;//物业手机号码
	private String receivable;//费用
	public String getParkexid() {
		return parkexid;
	}
	public void setParkexid(String parkexid) {
		this.parkexid = parkexid;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLicenseplate() {
		return licenseplate;
	}
	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReceivable() {
		return receivable;
	}
	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}
	public parkexpenses() {
		super();
	}
	public parkexpenses(String parkexid, String name, String address, String price, String licenseplate,
			String firsttime, String factorytime, String mobile, String receivable) {
		super();
		this.parkexid = parkexid;
		this.name = name;
		this.address = address;
		this.price = price;
		this.licenseplate = licenseplate;
		this.firsttime = firsttime;
		this.factorytime = factorytime;
		this.mobile = mobile;
		this.receivable = receivable;
	}
}
