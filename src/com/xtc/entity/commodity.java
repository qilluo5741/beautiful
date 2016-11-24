package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="commodity")
public class commodity {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String commodityid;
	private String commodiimg;//'商品图片',
	private String commodiname;//'商品名字',
	private String commodiprice;//'商品价格',
	private int commodicount;//'商品总数',
	private String goodproperid;//'商品外键',
	private String commoditime;//'商品时间'
	public String getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(String commodityid) {
		this.commodityid = commodityid;
	}
	public String getCommodiimg() {
		return commodiimg;
	}
	public void setCommodiimg(String commodiimg) {
		this.commodiimg = commodiimg;
	}
	public String getCommodiname() {
		return commodiname;
	}
	public void setCommodiname(String commodiname) {
		this.commodiname = commodiname;
	}
	public String getCommodiprice() {
		return commodiprice;
	}
	public void setCommodiprice(String commodiprice) {
		this.commodiprice = commodiprice;
	}
	public int getCommodicount() {
		return commodicount;
	}
	public void setCommodicount(int commodicount) {
		this.commodicount = commodicount;
	}
	public String getGoodproperid() {
		return goodproperid;
	}
	public void setGoodproperid(String goodproperid) {
		this.goodproperid = goodproperid;
	}
	public String getCommoditime() {
		return commoditime;
	}
	public void setCommoditime(String commoditime) {
		this.commoditime = commoditime;
	}
	public commodity(String commodityid, String commodiimg, String commodiname, String commodiprice, int commodicount,
			String goodproperid, String commoditime) {
		this.commodityid = commodityid;
		this.commodiimg = commodiimg;
		this.commodiname = commodiname;
		this.commodiprice = commodiprice;
		this.commodicount = commodicount;
		this.goodproperid = goodproperid;
		this.commoditime = commoditime;
	}
	public commodity() {}
}
