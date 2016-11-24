package com.xtc.entity.dto;

public class orderanduser {
	private String id;
	//private String parkId;
	
	/*public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	//select u.id from sys_user u where u.parkId in 
	//(SELECT o.parkId from order_info o where o.order_num='64e0e04b-dd3e-45b9-9744-f0825c4960b1')
}
