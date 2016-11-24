package com.xtc.entity;//

import java.io.Serializable;//

import javax.persistence.Column;//
import javax.persistence.Entity;//
import javax.persistence.GeneratedValue;//
import javax.persistence.Id;//
import javax.persistence.Table;//

import org.hibernate.annotations.GenericGenerator;//

@SuppressWarnings("serial")
@Entity
@Table(name="sys_park")
public class Park implements Serializable {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	 private String id;// varchar(36) NOT NULL COMMENT '主键',
	 private String name;// varchar(36) DEFAULT NULL COMMENT '名字',
	 private String address;// varchar(100) DEFAULT NULL COMMENT '地址',
	 private String street;// varchar(30) DEFAULT NULL COMMENT '街道',
	 private String district;// varchar(20) DEFAULT NULL COMMENT '区',
	 private String city;// varchar(20) DEFAULT NULL COMMENT '停车场类别',
	 private String capacity;// int(11) DEFAULT NULL COMMENT '停车位数量',
	 private String carnum;//车位产权
	 private double location_x;// decimal(36,6) DEFAULT NULL COMMENT '坐标',
	 private double location_y;// decimal(36,6) DEFAULT NULL COMMENT '坐标',
	 private String telephone;// varchar(11) DEFAULT NULL COMMENT '联系电话',
	 private String type;// varchar(12) DEFAULT NULL COMMENT '停车位类型',
	 private String cost;// varchar(255) DEFAULT NULL COMMENT '收费模式',
	 private String status;// varchar(1) DEFAULT NULL COMMENT '状态 0:可预约 1：不可预约\r\n            \r\n            公共、共有、路内默认状态为1\r\n            个人专有产权默认状态为0',
	 private String is_cooperate;// varchar(1) DEFAULT '0' COMMENT '是否合作 0:未合作 1：合作',
	 private String open_time;// varchar(30) DEFAULT NULL COMMENT '开放时间',
	 private String start_time;// varchar(30) DEFAULT NULL,
	 private String end_time;// varchar(30) DEFAULT NULL,
	 private String contract_num;// varchar(32) DEFAULT NULL,物业账号；
	 private String entry_address;// varchar(200) DEFAULT NULL COMMENT '入口地址',
	 private String cost_type;// varchar(2) DEFAULT NULL COMMENT '收费模式类型',1
	 private String price;//varchar(20) DEFAULT '',停车位价格
	 private String property;//` varchar(20) DEFAULT NULL,
	 private String category;//` varchar(20) DEFAULT NULL,
	 private String userid;
	 private String moralid;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public double getLocation_x() {
		return location_x;
	}
	public void setLocation_x(double location_x) {
		this.location_x = location_x;
	}
	public double getLocation_y() {
		return location_y;
	}
	public void setLocation_y(double location_y) {
		this.location_y = location_y;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIs_cooperate() {
		return is_cooperate;
	}
	public void setIs_cooperate(String is_cooperate) {
		this.is_cooperate = is_cooperate;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getContract_num() {
		return contract_num;
	}
	public void setContract_num(String contract_num) {
		this.contract_num = contract_num;
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
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMoralid() {
		return moralid;
	}
	public void setMoralid(String moralid) {
		this.moralid = moralid;
	}
	public Park() {
	}
	public Park(String id, String name, String address, String street, String district, String city, String capacity,
			String carnum, double location_x, double location_y, String telephone, String type, String cost,
			String status, String is_cooperate, String open_time, String start_time, String end_time,
			String contract_num, String entry_address, String cost_type, String price, String property, String category,
			String userid, String moralid) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.street = street;
		this.district = district;
		this.city = city;
		this.capacity = capacity;
		this.carnum = carnum;
		this.location_x = location_x;
		this.location_y = location_y;
		this.telephone = telephone;
		this.type = type;
		this.cost = cost;
		this.status = status;
		this.is_cooperate = is_cooperate;
		this.open_time = open_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.contract_num = contract_num;
		this.entry_address = entry_address;
		this.cost_type = cost_type;
		this.price = price;
		this.property = property;
		this.category = category;
		this.userid = userid;
		this.moralid = moralid;
	}
}
