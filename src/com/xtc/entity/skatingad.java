package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="skatingad")
public class skatingad {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String skatingadid;
	private String skatingtype;
	private String skatingurl;
	private String skatpicture;
	private String skattime;
	public String getSkatingadid() {
		return skatingadid;
	}
	public void setSkatingadid(String skatingadid) {
		this.skatingadid = skatingadid;
	}
	public String getSkatingtype() {
		return skatingtype;
	}
	public void setSkatingtype(String skatingtype) {
		this.skatingtype = skatingtype;
	}
	public String getSkatingurl() {
		return skatingurl;
	}
	public void setSkatingurl(String skatingurl) {
		this.skatingurl = skatingurl;
	}
	public String getSkatpicture() {
		return skatpicture;
	}
	public void setSkatpicture(String skatpicture) {
		this.skatpicture = skatpicture;
	}
	public String getSkattime() {
		return skattime;
	}
	public void setSkattime(String skattime) {
		this.skattime = skattime;
	}
	
	public skatingad() {
	}
	public skatingad(String skatingadid, String skatingtype, String skatingurl, String skatpicture, String skattime) {
		this.skatingadid = skatingadid;
		this.skatingtype = skatingtype;
		this.skatingurl = skatingurl;
		this.skatpicture = skatpicture;
		this.skattime = skattime;
	}
}
