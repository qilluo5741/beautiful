package com.xtc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 物业创建收费员记录
 * @author Administrator
 */
@Entity
@Table(name="tollRecords")
public class tollRecords {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String tollRid;
	private String tollname;
	private String tollRphone;//'物业手机号',
	private String tollRrecphone;//'收费员手机',
	private Date tollRtime;//创建时间时间',
	private String tollRremname;
	private String parkid;//停车场id'
	public String getTollRid(){
		return tollRid;
	}
	public void setTollRid(String tollRid) {
		this.tollRid = tollRid;
	}
	public String getTollname() {
		return tollname;
	}
	public void setTollname(String tollname) {
		this.tollname = tollname;
	}
	public String getTollRphone() {
		return tollRphone;
	}
	public void setTollRphone(String tollRphone) {
		this.tollRphone = tollRphone;
	}
	public String getTollRrecphone() {
		return tollRrecphone;
	}
	public void setTollRrecphone(String tollRrecphone) {
		this.tollRrecphone = tollRrecphone;
	}
	public Date getTollRtime() {
		return tollRtime;
	}
	public void setTollRtime(Date tollRtime) {
		this.tollRtime = tollRtime;
	}
	public String getTollRremname() {
		return tollRremname;
	}
	public void setTollRremname(String tollRremname) {
		this.tollRremname = tollRremname;
	}
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public tollRecords() {
	}
	public tollRecords(String tollRid, String tollname,String tollRphone, String tollRrecphone,
			Date tollRtime, String tollRremname, String parkid) {
		this.tollRid = tollRid;
		this.tollname = tollname;
		this.tollRphone = tollRphone;
		this.tollRrecphone = tollRrecphone;
		this.tollRtime = tollRtime;
		this.tollRremname = tollRremname;
		this.parkid = parkid;
	}
}
