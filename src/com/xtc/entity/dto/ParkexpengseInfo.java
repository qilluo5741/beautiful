package com.xtc.entity.dto;

import java.util.List;

public class ParkexpengseInfo {
	private long totalRecords;
	private List<Parkex> pk;
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<Parkex> getPk() {
		return pk;
	}
	public void setPk(List<Parkex> pk) {
		this.pk = pk;
	}

}
