package com.xtc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 收藏夹类
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="favorite")
public class favorite implements Serializable {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private  String id;
    //用户ID
    private String userId;
    //停车场id
    private String parkId;
    private String location_x;// decimal(36,6) DEFAULT NULL COMMENT '坐标',
	private String location_y;// decimal(36,6) DEFAULT NULL COMMENT '坐标',
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getLocation_x() {
		return location_x;
	}
	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}
	public String getLocation_y() {
		return location_y;
	}
	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}
	public favorite() {
	}
	public favorite(String id, String userId, String parkId, String location_x, String location_y) {
		this.id = id;
		this.userId = userId;
		this.parkId = parkId;
		this.location_x = location_x;
		this.location_y = location_y;
	}
}
