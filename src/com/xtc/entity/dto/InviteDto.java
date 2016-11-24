package com.xtc.entity.dto;

public class InviteDto {
	private String startDate;//'邀请时间',
	private String rtartmoble;//'邀请的手机号码'
	private String state;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRtartmoble() {
		return rtartmoble;
	}
	public void setRtartmoble(String rtartmoble) {
		this.rtartmoble = rtartmoble;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
