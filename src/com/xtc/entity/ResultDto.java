package com.xtc.entity;

public class ResultDto {
	private int state;//状态
	private String msg;//状态
	private Object result;//结果集
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public ResultDto(int state, Object result) {
		this.state = state;
		this.result = result;
	}
	public ResultDto() {
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResultDto(int state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}
	public ResultDto(int state, String msg, Object result) {
		super();
		this.state = state;
		this.msg = msg;
		this.result = result;
	}
}
