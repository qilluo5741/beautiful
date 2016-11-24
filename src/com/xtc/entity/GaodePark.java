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
@Table(name="GaodePark")
public class GaodePark implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
    private String location;
    private String tel;
    private String distance;
    private String cost;
    private String name;
    private String exit_location;
    private String business_area;
    private String email;
    private String address;
    private String pname;
    private String cityname;
    private String adname;
    private String entr_location;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExit_location() {
		return exit_location;
	}
	public void setExit_location(String exit_location) {
		this.exit_location = exit_location;
	}
	public String getBusiness_area() {
		return business_area;
	}
	public void setBusiness_area(String business_area) {
		this.business_area = business_area;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getEntr_location() {
		return entr_location;
	}
	public void setEntr_location(String entr_location) {
		this.entr_location = entr_location;
	}
	public GaodePark(String id, String location, String tel, String distance,
			String cost, String name, String exit_location,
			String business_area, String email, String address, String pname,
			String cityname, String adname, String entr_location) {
		super();
		this.id = id;
		this.location = location;
		this.tel = tel;
		this.distance = distance;
		this.cost = cost;
		this.name = name;
		this.exit_location = exit_location;
		this.business_area = business_area;
		this.email = email;
		this.address = address;
		this.pname = pname;
		this.cityname = cityname;
		this.adname = adname;
		this.entr_location = entr_location;
	}
	public GaodePark() {
	}
}
