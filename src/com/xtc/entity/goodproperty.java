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
@Table(name="goodproperty")
public class goodproperty implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String goodproperid;//商品劵id',
	private String goodproname;//'劵名字',
	private String repairlist;// '商品价格',
	private double pressprice;// '享泊优惠价',
	private String goodproperremeck;//'劵说明',
	private String repmenuid;//'外键商品id'
	public String getGoodproperid() {
		return goodproperid;
	}
	public void setGoodproperid(String goodproperid) {
		this.goodproperid = goodproperid;
	}
	public String getGoodproname() {
		return goodproname;
	}
	public void setGoodproname(String goodproname) {
		this.goodproname = goodproname;
	}
	public String getRepairlist() {
		return repairlist;
	}
	public void setRepairlist(String repairlist) {
		this.repairlist = repairlist;
	}
	public double getPressprice() {
		return pressprice;
	}
	public void setPressprice(double pressprice) {
		this.pressprice = pressprice;
	}
	public String getGoodproperremeck() {
		return goodproperremeck;
	}
	public void setGoodproperremeck(String goodproperremeck) {
		this.goodproperremeck = goodproperremeck;
	}
	public String getRepmenuid() {
		return repmenuid;
	}
	public void setRepmenuid(String repmenuid) {
		this.repmenuid = repmenuid;
	}
	public goodproperty() {
	}
	public goodproperty(String goodproperid, String goodproname, String repairlist, double pressprice,
			String goodproperremeck, String repmenuid) {
		this.goodproperid = goodproperid;
		this.goodproname = goodproname;
		this.repairlist = repairlist;
		this.pressprice = pressprice;
		this.goodproperremeck = goodproperremeck;
		this.repmenuid = repmenuid;
	}
}
