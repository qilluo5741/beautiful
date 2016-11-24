package com.xtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_park")
public class parking{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	 private String id;
	 private String name;
	 private String address;
	 private String street;
	 private String district;
	 private String city;
	 private String capacity;
	 private String carnum;
	 private String telephone;
	 private String type;
	 private String cost;
	 private String status;
	 private String is_cooperate;
	 private String open_time;
	 private String start_time;
	 private String end_time;
	 private String contract_num;
	 private String entry_address;
	 private String cost_type;
	 private String price;
	 private String property;
	 private String category;
	 private String userid;
	 private String reservation;
	 private String dividedinto;
	 private double subscription;
	 private String moralid;
	public String getId(){
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
	public Double getSubscription() {
		return subscription;
	}
	public void setSubscription(Double subscription) {
		this.subscription = subscription;
	}
	public String getMoralid() {
		return moralid;
	}
	public void setMoralid(String moralid) {
		this.moralid = moralid;
	}
	public parking() {
		super();
	}
	public parking(String id, String name, String address, String street, String district, String city, String capacity,
			String carnum, String telephone, String type, String cost, String status, String is_cooperate,
			String open_time, String start_time, String end_time, String contract_num, String entry_address,
			String cost_type, String price, String property, String category, String userid, String reservation,
			String dividedinto, Double subscription, String moralid) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.street = street;
		this.district = district;
		this.city = city;
		this.capacity = capacity;
		this.carnum = carnum;
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
		this.reservation = reservation;
		this.dividedinto = dividedinto;
		this.subscription = subscription;
		this.moralid = moralid;
	}
}
