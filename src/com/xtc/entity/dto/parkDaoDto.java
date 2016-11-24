package com.xtc.entity.dto;

public class parkDaoDto {
	
	 private String city;// varchar(20) DEFAULT NULL COMMENT '停车场类别',
	 private String address;// varchar(100) DEFAULT NULL COMMENT '地址',
	 private String entry_address;// varchar(200) DEFAULT NULL COMMENT '入口地址',
	 private String capacity;// int(11) DEFAULT NULL COMMENT '停车位数量',
	 private String carnum;//停车产权车位
	 private String type;// varchar(12) DEFAULT NULL COMMENT '类型',
	 private String price;//停车场价格
	 private String is_cooperate;// varchar(1) DEFAULT '0' COMMENT '是否合作 0:未合作 1：合作',
	 private String property;
	 private String cost;// varchar(255) DEFAULT NULL COMMENT '收费模式',
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEntry_address() {
		return entry_address;
	}
	public void setEntry_address(String entry_address) {
		this.entry_address = entry_address;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIs_cooperate() {
		return is_cooperate;
	}
	public void setIs_cooperate(String is_cooperate) {
		this.is_cooperate = is_cooperate;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
}
