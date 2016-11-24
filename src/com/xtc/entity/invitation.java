package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="invitation")
public class invitation {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String invitaId;
	private String startDate;//'邀请时间',
	private String date_created;//'注册时间'，
	private String startmoble;//'邀请人手机号码'，
	private String rtartmoble;//'邀请的手机号码'，
	private String state;
	public String getRtartmoble() {
		return rtartmoble;
	}
	public void setRtartmoble(String rtartmoble) {
		this.rtartmoble = rtartmoble;
	}
	public String getStartmoble() {
		return startmoble;
	}
	public void setStartmoble(String startmoble) {
		this.startmoble = startmoble;
	}
	public String getInvitaId() {
		return invitaId;
	}
	public void setInvitaId(String invitaId) {
		this.invitaId = invitaId;
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
	public invitation() {
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public invitation(String invitaId, String startDate, String date_created,
			String startmoble, String rtartmoble, String state) {
		this.invitaId = invitaId;
		this.startDate = startDate;
		this.date_created = date_created;
		this.startmoble = startmoble;
		this.rtartmoble = rtartmoble;
		this.state = state;
	}
}
