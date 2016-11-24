package com.xtc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@SuppressWarnings("serial")
@Entity
@Table(name="PersonSite")
public class PersonSite implements Serializable {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
	private String userId;
	private String parkId;
	//停车位编
	private int num;
	//产权证明
	private String certificate;
	//上传时间
	private Date loadTime;
	//审核时间
	private Date auditTime;
	//审核状
	private String auditStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Date getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public PersonSite() {
	}
	public PersonSite(String id, String userId, String parkId, int num,
			String certificate, Date loadTime, Date auditTime,
			String auditStatus) {
		this.id = id;
		this.userId = userId;
		this.parkId = parkId;
		this.num = num;
		this.certificate = certificate;
		this.loadTime = loadTime;
		this.auditTime = auditTime;
		this.auditStatus = auditStatus;
	}
}
