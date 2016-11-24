package com.xtc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 收款账户的信息
 * @author ibm
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="bank")
public class Bank implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	   private String bid         ;   //账户编号
	   private String accountname ;   //账户名称
	   private String accountnumber;   //账户号码
	   private String bankname    ;   //开户银行
	   private String bankplace   ;   //开户地区
	   private String subbank     ;   //开户支行
	   private String userid      ;   //用户编号（user）
	   
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumbe(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankplace() {
		return bankplace;
	}
	public void setBankplace(String bankplace) {
		this.bankplace = bankplace;
	}
	public String getSubbank() {
		return subbank;
	}
	public void setSubbank(String subbank) {
		this.subbank = subbank;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Bank(String bid, String accountname, String accountnumber,
			String bankname, String bankplace, String subbank, String userid) {
		this.bid = bid;
		this.accountname = accountname;
		this.accountnumber = accountnumber;
		this.bankname = bankname;
		this.bankplace = bankplace;
		this.subbank = subbank;
		this.userid = userid;
	}
	public Bank() {
	}
}