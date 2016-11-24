package com.xtc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="relation")
public class relation {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String relaid;
	private String invideCode;
	private String relationCode;
	private Date invideDate;
	public String getRelaid() {
		return relaid;
	}
	public void setRelaid(String relaid) {
		this.relaid = relaid;
	}
	public String getInvideCode() {
		return invideCode;
	}
	public void setInvideCode(String invideCode) {
		this.invideCode = invideCode;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	public Date getInvideDate() {
		return invideDate;
	}
	public void setInvideDate(Date invideDate) {
		this.invideDate = invideDate;
	}
	public relation() {
	}
	public relation(String relaid, String invideCode, String relationCode,
			Date invideDate) {
		this.relaid = relaid;
		this.invideCode = invideCode;
		this.relationCode = relationCode;
		this.invideDate = invideDate;
	}
}
