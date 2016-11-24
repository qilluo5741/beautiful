package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="jpushinfo")
public class jpushinfo {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String jpushid;
    private String userid;
    private String regid;
	public String getJpushid() {
		return jpushid;
	}
	public void setJpushid(String jpushid) {
		this.jpushid = jpushid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public jpushinfo() {
	}
	public jpushinfo(String jpushid, String userid, String regid) {
		this.jpushid = jpushid;
		this.userid = userid;
		this.regid = regid;
	}
}
