package com.xtc.entity.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderunDto implements  Serializable{
	private String commodityid;
	private int inordcount;
	public String getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(String commodityid) {
		this.commodityid = commodityid;
	}
	public int getInordcount() {
		return inordcount;
	}
	public void setInordcount(int inordcount) {
		this.inordcount = inordcount;
	}
}
