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
@Table(name="stateinfo")
public class Stateinfo implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String  sid;
    private String  statetype;
    private String  username;
    private String  statetime;
    private String  parkid;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStatetype() {
		return statetype;
	}
	public void setStatetype(String statetype) {
		this.statetype = statetype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatetime() {
		return statetime;
	}
	public void setStatetime(String statetime) {
		this.statetime = statetime;
	}
	
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public Stateinfo(String sid, String statetype, String username,
			String statetime, String parkid) {
		this.sid = sid;
		this.statetype = statetype;
		this.username = username;
		this.statetime = statetime;
		this.parkid = parkid;
	}
	public Stateinfo() {
	}
}







