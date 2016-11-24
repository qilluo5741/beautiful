package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_park")
public class syspark {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	 private String id;//'主键',
	 private String name;//'名字',
	 private String address;//'地址',
	 private String city;//'停车场类别',
	 private String capacity;//'停车位数量',
	 private String carnum;//车位产权
	 private String type;//'停车位类型',
	 private String cost;//'收费模式',
	 private String is_cooperate;//'是否合作 0:未合作 1：合作',
	 private String entry_address;//'入口地址',
	 private String cost_type;//'收费模式类型',
	 private String price;//停车位价格
	 private String reservation;
	 private String dividedinto;
	 private double subscription;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getIs_cooperate() {
		return is_cooperate;
	}
	public void setIs_cooperate(String is_cooperate) {
		this.is_cooperate = is_cooperate;
	}
	public String getEntry_address() {
		return entry_address;
	}
	public void setEntry_address(String entry_address) {
		this.entry_address = entry_address;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getDividedinto() {
		return dividedinto;
	}
	public void setDividedinto(String dividedinto) {
		this.dividedinto = dividedinto;
	}
	
	public double getSubscription() {
		return subscription;
	}
	public void setSubscription(double subscription) {
		this.subscription = subscription;
	}
	public syspark() {
		super();
	}
	public syspark(String id, String name, String address, String city, String capacity, String carnum, String type,
			String cost, String is_cooperate, String entry_address, String cost_type, String price, String reservation,
			String dividedinto, double subscription) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.capacity = capacity;
		this.carnum = carnum;
		this.type = type;
		this.cost = cost;
		this.is_cooperate = is_cooperate;
		this.entry_address = entry_address;
		this.cost_type = cost_type;
		this.price = price;
		this.reservation = reservation;
		this.dividedinto = dividedinto;
		this.subscription = subscription;
	}
}
