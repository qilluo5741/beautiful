package com.xtc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="userinfo")
public class Userinfo implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
    private String  usaerid;
	private String  userName;
	private String  userPwd;
	private String  userimg;
	private int  userSex;
	private int  userStatus;
	private String  userRoid;
	private String  userDate;
	public String getUsaerid() {
		return usaerid;
	}
	public void setUsaerid(String usaerid) {
		this.usaerid = usaerid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserRoid() {
		return userRoid;
	}
	public void setUserRoid(String userRoid) {
		this.userRoid = userRoid;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public Userinfo() {
	}
	public Userinfo(String usaerid, String userName, String userPwd, String userimg, int userSex, int userStatus,
			String userRoid, String userDate) {
		this.usaerid = usaerid;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userimg = userimg;
		this.userSex = userSex;
		this.userStatus = userStatus;
		this.userRoid = userRoid;
		this.userDate = userDate;
	}
}
