package com.xtc.entity.dto;

public class invitationDto {
	private String startDate;//'邀请时间',
	private String date_created;//'注册时间'，
	private String rtartmoble;//'邀请的手机号码'，
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getRtartmoble() {
		return rtartmoble;
	}
	public void setRtartmoble(String rtartmoble) {
		this.rtartmoble = rtartmoble;
	}
}
