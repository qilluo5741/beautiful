package com.xtc.entity;

public class parkuser{
	private String mobile;//varchar(11) DEFAULT NULL COMMENT '登录名',
	private String name;//varchar(20) DEFAULT NULL COMMENT '姓名',
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
