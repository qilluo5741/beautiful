package com.xtc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 企业停车�?
 * @author weidai
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CompanySite")
public class CompanySite implements Serializable {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String id;
    private String license;
    private String orgCode;
    private String taxReg;
    private String parkingLot;
    private String accountName;
    private String account;
    private String bankName;
    private Date loadTime;
    private Date auditTime;
    private String auditStatus;
    private String parkId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTaxReg() {
		return taxReg;
	}
	public void setTaxReg(String taxReg) {
		this.taxReg = taxReg;
	}
	public String getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public CompanySite(String id, String license, String orgCode,
			String taxReg, String parkingLot, String accountName,
			String account, String bankName, Date loadTime, Date auditTime,
			String auditStatus, String parkId) {
		super();
		this.id = id;
		this.license = license;
		this.orgCode = orgCode;
		this.taxReg = taxReg;
		this.parkingLot = parkingLot;
		this.accountName = accountName;
		this.account = account;
		this.bankName = bankName;
		this.loadTime = loadTime;
		this.auditTime = auditTime;
		this.auditStatus = auditStatus;
		this.parkId = parkId;
	}
	public CompanySite() {
	}
}
