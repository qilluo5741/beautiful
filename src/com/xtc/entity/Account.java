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
@Table(name="Account")
public class Account implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	
    private String id;
    //可用余额
    private double balance;
    //用户ID
	private String userId;
	//付款或充值数
    private String number;
    //说明
    private String content;
    //付款或充值日
    private String date_creat;
    //类型
    private String type;
    //冻结余额
    private double freezeMoney;
    
    public String getUserId() {
		return userId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getDate_creat() {
		return date_creat;
	}
	public void setDate_creat(String date_creat) {
		this.date_creat = date_creat;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Account() {
	}
	public Account(String id, double balance, String userId, String number,
			String content, String date_creat, String type, double freezeMoney) {
		this.id = id;
		this.balance = balance;
		this.userId = userId;
		this.number = number;
		this.content = content;
		this.date_creat = date_creat;
		this.type = type;
		this.freezeMoney = freezeMoney;
	}
	public Account(double balance, double freezeMoney) {
		this.balance = balance;
		this.freezeMoney = freezeMoney;
	}
}
