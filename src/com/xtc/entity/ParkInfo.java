
 /**
 * Project Name:enjoy-parking
 * File Name:ParkInfo.java
 * Package Name:com.xtc.park.models
 * Date:2015�?1�?0日下�?:12:45
 * Copyright (c) 2015, daiwei_1001@sharebo.cn All Rights Reserved.
 *
*/

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
@Table(name="ParkInfo")
public class ParkInfo implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
    private String vehicleId;
    private String userId;
    private String parkId;
    private Date parkStartTime;
    private Date parkEndTime;
    private Date exchangeTime;
    private Date orderRequestTime;
    private Date orderResponseTime;
    private Date daiboRequestTime;
    private Date daiboResponseTime;
    private Date daiboConfirmTime;
    private Date payTime;
    private String payType;
    private Date prepayTime;
    private Date serviceDate;
    private String serviceType;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
	public Date getParkStartTime() {
		return parkStartTime;
	}
	public void setParkStartTime(Date parkStartTime) {
		this.parkStartTime = parkStartTime;
	}
	public Date getParkEndTime() {
		return parkEndTime;
	}
	public void setParkEndTime(Date parkEndTime) {
		this.parkEndTime = parkEndTime;
	}
	public Date getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	public Date getOrderRequestTime() {
		return orderRequestTime;
	}
	public void setOrderRequestTime(Date orderRequestTime) {
		this.orderRequestTime = orderRequestTime;
	}
	public Date getOrderResponseTime() {
		return orderResponseTime;
	}
	public void setOrderResponseTime(Date orderResponseTime) {
		this.orderResponseTime = orderResponseTime;
	}
	public Date getDaiboRequestTime() {
		return daiboRequestTime;
	}
	public void setDaiboRequestTime(Date daiboRequestTime) {
		this.daiboRequestTime = daiboRequestTime;
	}
	public Date getDaiboResponseTime() {
		return daiboResponseTime;
	}
	public void setDaiboResponseTime(Date daiboResponseTime) {
		this.daiboResponseTime = daiboResponseTime;
	}
	public Date getDaiboConfirmTime() {
		return daiboConfirmTime;
	}
	public void setDaiboConfirmTime(Date daiboConfirmTime) {
		this.daiboConfirmTime = daiboConfirmTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getPrepayTime() {
		return prepayTime;
	}
	public void setPrepayTime(Date prepayTime) {
		this.prepayTime = prepayTime;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public ParkInfo() {
	}
	public ParkInfo(String id, String vehicleId, String userId, String parkId,
			Date parkStartTime, Date parkEndTime, Date exchangeTime,
			Date orderRequestTime, Date orderResponseTime,
			Date daiboRequestTime, Date daiboResponseTime,
			Date daiboConfirmTime, Date payTime, String payType,
			Date prepayTime, Date serviceDate, String serviceType) {
		this.id = id;
		this.vehicleId = vehicleId;
		this.userId = userId;
		this.parkId = parkId;
		this.parkStartTime = parkStartTime;
		this.parkEndTime = parkEndTime;
		this.exchangeTime = exchangeTime;
		this.orderRequestTime = orderRequestTime;
		this.orderResponseTime = orderResponseTime;
		this.daiboRequestTime = daiboRequestTime;
		this.daiboResponseTime = daiboResponseTime;
		this.daiboConfirmTime = daiboConfirmTime;
		this.payTime = payTime;
		this.payType = payType;
		this.prepayTime = prepayTime;
		this.serviceDate = serviceDate;
		this.serviceType = serviceType;
	}
    
}

