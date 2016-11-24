package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="information")
public class information {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String mationid;
	private Long duration;
	private int jl;
	private Long times;
	private String userid;
	public String getMationid() {
		return mationid;
	}
	public void setMationid(String mationid) {
		this.mationid = mationid;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public int getJl() {
		return jl;
	}
	public void setJl(int jl) {
		this.jl = jl;
	}
	public Long getTimes() {
		return times;
	}
	public void setTimes(Long times) {
		this.times = times;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public information() {
		super();
	}
	public information(String mationid, Long duration, int jl, Long times, String userid) {
		super();
		this.mationid = mationid;
		this.duration = duration;
		this.jl = jl;
		this.times = times;
		this.userid = userid;
	}
}
