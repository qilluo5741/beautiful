
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
@Table(name="vehicle_license")
public class VehicleLicense implements  Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String id;
	private String userId;
	private String plate_no;
	private String vehicle_type;
	private String owner;
	private String address;
	private String user_character;
	private String modes;
	private String vin;
	private String engine_no;
	private Date register_date;
	private Date issue_date;
	private String vehicle_license_front;
	private String vehicle_license_back;
	private Date date_updated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlate_no() {
		return plate_no;
	}
	public void setPlate_no(String plate_no) {
		this.plate_no = plate_no;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUser_character() {
		return user_character;
	}
	public void setUser_character(String user_character) {
		this.user_character = user_character;
	}
	public String getModes() {
		return modes;
	}
	public void setModes(String modes) {
		this.modes = modes;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public String getVehicle_license_front() {
		return vehicle_license_front;
	}
	public void setVehicle_license_front(String vehicle_license_front) {
		this.vehicle_license_front = vehicle_license_front;
	}
	public String getVehicle_license_back() {
		return vehicle_license_back;
	}
	public void setVehicle_license_back(String vehicle_license_back) {
		this.vehicle_license_back = vehicle_license_back;
	}
	public Date getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}
	public VehicleLicense() {
	}
	public VehicleLicense(String id, String userId, String plate_no,
			String vehicle_type, String owner, String address,
			String user_character, String modes, String vin, String engine_no,
			Date register_date, Date issue_date, String vehicle_license_front,
			String vehicle_license_back, Date date_updated) {
		this.id = id;
		this.userId = userId;
		this.plate_no = plate_no;
		this.vehicle_type = vehicle_type;
		this.owner = owner;
		this.address = address;
		this.user_character = user_character;
		this.modes = modes;
		this.vin = vin;
		this.engine_no = engine_no;
		this.register_date = register_date;
		this.issue_date = issue_date;
		this.vehicle_license_front = vehicle_license_front;
		this.vehicle_license_back = vehicle_license_back;
		this.date_updated = date_updated;
	}
}

