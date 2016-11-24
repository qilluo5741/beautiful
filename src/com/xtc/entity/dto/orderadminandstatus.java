package com.xtc.entity.dto;

public class orderadminandstatus {
//select u.is_admin,u.status from sys_user u where u.id='83b112c7-9717-11e5-81ee-97a636e6c9b7';
	private String is_admin;
	private String  status;
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
