
 /**
 * Project Name:enjoy-parking
 * File Name:Advise.java
 * Package Name:com.xtc.park.models
 * Date:2015�?1�?4日下�?:30:25
 * Copyright (c) 2015, daiwei_1001@sharebo.cn All Rights Reserved.
 *
*/

package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Advise")
public class Advise{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    //id
    private String id;
    //用户id
    private String userId;
    //内容
    private String content;
    //图片路径
    private String picture_url;
    //类型
    private String type;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Advise() {
	}
	public Advise(String id, String userId, String content, String picture_url, String type) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.picture_url = picture_url;
		this.type = type;
	}
}

