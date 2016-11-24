package com.xtc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="paymentrecords")
public class paymentrecords {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String  paymentid;
	private String userid;//'用户id',
	private Date paymenttime;//'支付时间',
	private double paymoney;// double(12,2) DEFAULT NULL COMMENT '支付金额',
	private int payalipay;//支付类型(0：余额，1支付宝，2微信,3退款)'
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(Date paymenttime) {
		this.paymenttime = paymenttime;
	}
	public double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(double paymoney) {
		this.paymoney = paymoney;
	}
	public int getPayalipay() {
		return payalipay;
	}
	public void setPayalipay(int payalipay) {
		this.payalipay = payalipay;
	}
	public paymentrecords() {
	}
	public paymentrecords(String paymentid, String userid, Date paymenttime, double paymoney, int payalipay) {
		this.paymentid = paymentid;
		this.userid = userid;
		this.paymenttime = paymenttime;
		this.paymoney = paymoney;
		this.payalipay = payalipay;
	}
}
