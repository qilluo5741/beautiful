package com.xtc.entity.dto;

public class OrderRecordInfo {
	private String name;//姓名
	private String mobile;//手机号码
	private String park_start_time;//开始停车时间
	private String park_end_time;//结束停车时间
	private String pay_time;
	private String plate_no;//车牌号
	private String oderstate;//接受状态 （1 ：已接受   0：已拒绝  2：等待   3:免预约费）
	private String order_num;
	private String starttiming;
	private String endtiming;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPark_start_time() {
		return park_start_time;
	}
	public void setPark_start_time(String park_start_time) {
		this.park_start_time = park_start_time;
	}
	public String getPark_end_time() {
		return park_end_time;
	}
	public void setPark_end_time(String park_end_time) {
		this.park_end_time = park_end_time;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getPlate_no() {
		return plate_no;
	}
	public void setPlate_no(String plate_no) {
		this.plate_no = plate_no;
	}
	public String getOderstate() {
		return oderstate;
	}
	public void setOderstate(String oderstate) {
		this.oderstate = oderstate;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getStarttiming() {
		return starttiming;
	}
	public void setStarttiming(String starttiming) {
		this.starttiming = starttiming;
	}
	public String getEndtiming() {
		return endtiming;
	}
	public void setEndtiming(String endtiming) {
		this.endtiming = endtiming;
	}
}
