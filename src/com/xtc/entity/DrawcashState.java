package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="drawcashState")
public class DrawcashState {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private  String  stateid;
	private  String  typea;
	private  String  typeb;
	private  String  typec;
	private  String  did;
	private  String  typeaTime;
	private  String  typebTime;
	private  String  typecTime;
	
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getTypea() {
		return typea;
	}
	public void setTypea(String typea) {
		this.typea = typea;
	}
	public String getTypeb() {
		return typeb;
	}
	public void setTypeb(String typeb) {
		this.typeb = typeb;
	}
	public String getTypec() {
		return typec;
	}
	public void setTypec(String typec) {
		this.typec = typec;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getTypeaTime() {
		return typeaTime;
	}
	public void setTypeaTime(String typeaTime) {
		this.typeaTime = typeaTime;
	}
	public String getTypebTime() {
		return typebTime;
	}
	public void setTypebTime(String typebTime) {
		this.typebTime = typebTime;
	}
	public String getTypecTime() {
		return typecTime;
	}
	public void setTypecTime(String typecTime) {
		this.typecTime = typecTime;
	}
	public DrawcashState() {
	}
	public DrawcashState(String stateid, String typea, String typeb,
			String typec, String did, String typeaTime, String typebTime,
			String typecTime) {
		super();
		this.stateid = stateid;
		this.typea = typea;
		this.typeb = typeb;
		this.typec = typec;
		this.did = did;
		this.typeaTime = typeaTime;
		this.typebTime = typebTime;
		this.typecTime = typecTime;
	}
	
	
}
