

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
@Table(name="Ident")
public class Ident implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
	private String email;
	private String vehicleLicenseFront;
	private String vehicleLicenseBack;
	private String driverLicenseFront;
	private String driverLicenseBack;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVehicleLicenseFront() {
		return vehicleLicenseFront;
	}
	public void setVehicleLicenseFront(String vehicleLicenseFront) {
		this.vehicleLicenseFront = vehicleLicenseFront;
	}
	public String getVehicleLicenseBack() {
		return vehicleLicenseBack;
	}
	public void setVehicleLicenseBack(String vehicleLicenseBack) {
		this.vehicleLicenseBack = vehicleLicenseBack;
	}
	public String getDriverLicenseFront() {
		return driverLicenseFront;
	}
	public void setDriverLicenseFront(String driverLicenseFront) {
		this.driverLicenseFront = driverLicenseFront;
	}
	public String getDriverLicenseBack() {
		return driverLicenseBack;
	}
	public void setDriverLicenseBack(String driverLicenseBack) {
		this.driverLicenseBack = driverLicenseBack;
	}
	public Ident(String id, String email, String vehicleLicenseFront,
			String vehicleLicenseBack, String driverLicenseFront,
			String driverLicenseBack) {
		this.id = id;
		this.email = email;
		this.vehicleLicenseFront = vehicleLicenseFront;
		this.vehicleLicenseBack = vehicleLicenseBack;
		this.driverLicenseFront = driverLicenseFront;
		this.driverLicenseBack = driverLicenseBack;
	}
	public Ident() {
	}
}

