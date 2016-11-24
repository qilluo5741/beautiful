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
@Table(name="repairmenu")
public class Repairmenu implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String repmenuid;//'商品菜单id',
	private String repairName;//'菜单名字',
	private String repairimge;
	public String getRepmenuid() {
		return repmenuid;
	}
	public void setRepmenuid(String repmenuid) {
		this.repmenuid = repmenuid;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public String getRepairimge() {
		return repairimge;
	}
	public void setRepairimge(String repairimge) {
		this.repairimge = repairimge;
	}
	public Repairmenu() {
	}
	public Repairmenu(String repmenuid, String repairName, String repairimge) {
		this.repmenuid = repmenuid;
		this.repairName = repairName;
		this.repairimge = repairimge;
	}
}
