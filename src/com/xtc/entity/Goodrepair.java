package com.xtc.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 商品详细表
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Goodrepair")
public class Goodrepair implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String repairid;//'商品id',
	private String repairName;//'商品名字',
	private String repNameimg;//'商品图标',
	private String repmenuid;//'商品图标',
	private String explanation;//商品说明',
	private String description;//'商品地图',
	private String repairaddress;//'商品地址',
	private String bookingline;//'预约电话'
	private String repairlist;// '商品价格',
	private String pressprice;// '享泊优惠价',
	private String goodproperid;
	public String getRepairid() {
		return repairid;
	}
	public String getRepairlist() {
		return repairlist;
	}
	public void setRepairlist(String repairlist) {
		this.repairlist = repairlist;
	}
	public String getPressprice() {
		return pressprice;
	}
	public void setPressprice(String pressprice) {
		this.pressprice = pressprice;
	}
	public void setRepairid(String repairid) {
		this.repairid = repairid;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public String getRepNameimg() {
		return repNameimg;
	}
	public void setRepNameimg(String repNameimg) {
		this.repNameimg = repNameimg;
	}
	public String getRepmenuid() {
		return repmenuid;
	}
	public void setRepmenuid(String repmenuid) {
		this.repmenuid = repmenuid;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRepairaddress() {
		return repairaddress;
	}
	public void setRepairaddress(String repairaddress) {
		this.repairaddress = repairaddress;
	}
	public String getBookingline() {
		return bookingline;
	}
	public void setBookingline(String bookingline) {
		this.bookingline = bookingline;
	}
	public String getGoodproperid() {
		return goodproperid;
	}
	public void setGoodproperid(String goodproperid) {
		this.goodproperid = goodproperid;
	}
	public Goodrepair() {
	}
	public Goodrepair(String repairid, String repairName, String repNameimg, String repmenuid, String explanation,
			String description, String repairaddress, String bookingline, String repairlist, String pressprice,
			String goodproperid) {
		this.repairid = repairid;
		this.repairName = repairName;
		this.repNameimg = repNameimg;
		this.repmenuid = repmenuid;
		this.explanation = explanation;
		this.description = description;
		this.repairaddress = repairaddress;
		this.bookingline = bookingline;
		this.repairlist = repairlist;
		this.pressprice = pressprice;
		this.goodproperid = goodproperid;
	}
}
