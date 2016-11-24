package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="toolversion")
public class toolversion {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String  toolverid;//'版本id',
	private String  toolversion;//'版本号',
	private String  tooleption;//'描述',
	private String  toolurl;//'路径',
	private String  toonumber;//'操作系统版本号'
	public String getToolverid() {
		return toolverid;
	}
	public void setToolverid(String toolverid) {
		this.toolverid = toolverid;
	}
	public String getToolversion() {
		return toolversion;
	}
	public void setToolversion(String toolversion) {
		this.toolversion = toolversion;
	}
	public String getTooleption() {
		return tooleption;
	}
	public void setTooleption(String tooleption) {
		this.tooleption = tooleption;
	}
	public String getToolurl() {
		return toolurl;
	}
	public void setToolurl(String toolurl) {
		this.toolurl = toolurl;
	}
	public String getToonumber() {
		return toonumber;
	}
	public void setToonumber(String toonumber) {
		this.toonumber = toonumber;
	}
	public toolversion() {
	}
	public toolversion(String toolverid, String toolversion, String tooleption, String toolurl, String toonumber) {
		this.toolverid = toolverid;
		this.toolversion = toolversion;
		this.tooleption = tooleption;
		this.toolurl = toolurl;
		this.toonumber = toonumber;
	}
}
