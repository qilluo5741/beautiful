package com.xtc.entity.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class unorderDto implements  Serializable{
	private String mobile;
	private String repmenuid;
	private String goodproperid;
	private String inordAddress;
	private String inordphone;
	private String inordUname;
	private List<OrderunDto> order;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRepmenuid() {
		return repmenuid;
	}
	public void setRepmenuid(String repmenuid) {
		this.repmenuid = repmenuid;
	}
	public String getGoodproperid() {
		return goodproperid;
	}
	public void setGoodproperid(String goodproperid) {
		this.goodproperid = goodproperid;
	}
	public String getInordAddress() {
		return inordAddress;
	}
	public void setInordAddress(String inordAddress) {
		this.inordAddress = inordAddress;
	}
	public String getInordphone() {
		return inordphone;
	}
	public void setInordphone(String inordphone) {
		this.inordphone = inordphone;
	}
	public String getInordUname() {
		return inordUname;
	}
	public void setInordUname(String inordUname) {
		this.inordUname = inordUname;
	}
	public List<OrderunDto> getOrder() {
		return order;
	}
	public void setOrder(List<OrderunDto> order) {
		this.order = order;
	}
}
