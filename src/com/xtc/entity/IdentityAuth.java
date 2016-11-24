package com.xtc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@SuppressWarnings("serial")
@Entity
@Table(name="IdentityAuth")
public class IdentityAuth implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
    //营业执照
    private String licence;
    //用户ID
    private String userId;
    //组织机构代码
    private String orgCode;
    //税务登记
    private String taxReg;
    //停车场资质管理证
    private String parkingLot;
    //银行?证明
    private String openAccount;
    //身份证正
    private String idcardFront;
    //身份证反
    private String idcardBack;
    //产权证明
    private String certificate;
    //小区停车
    private String parkOwner;
    //类型
    private String type;
    //驾驶证正
    private String driverFront;
    //驾驶证反
    private String driverBack;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getOpenAccount() {
		return openAccount;
	}
	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
	public String getIdcardFront() {
		return idcardFront;
	}
	public void setIdcardFront(String idcardFront) {
		this.idcardFront = idcardFront;
	}
	public String getIdcardBack() {
		return idcardBack;
	}
	public void setIdcardBack(String idcardBack) {
		this.idcardBack = idcardBack;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getParkOwner() {
		return parkOwner;
	}
	public void setParkOwner(String parkOwner) {
		this.parkOwner = parkOwner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriverFront() {
		return driverFront;
	}
	public void setDriverFront(String driverFront) {
		this.driverFront = driverFront;
	}
	public String getDriverBack() {
		return driverBack;
	}
	public void setDriverBack(String driverBack) {
		this.driverBack = driverBack;
	}
    
}
