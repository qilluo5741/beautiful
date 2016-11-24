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
@Table(name="Casher")
public class Casher implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
	
	private String idcardFront;

	private String idcardBack;
	
	private String parkId;
	
	public Casher() {
	}
	public Casher(String id, String idcardFront, String idcardBack,
			String parkId) {
		this.id = id;
		this.idcardFront = idcardFront;
		this.idcardBack = idcardBack;
		this.parkId = parkId;
	}
	public String getIdcardFront() {
		return idcardFront;
	}
	public void setIdcardFront(String idcardFront) {
		this.idcardFront = idcardFront;
	}
	public String getIdcardBack() {
		return idcardBack;
	}
	public void setIdcardBack(String idcardBack) {
		this.idcardBack = idcardBack;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	
}
