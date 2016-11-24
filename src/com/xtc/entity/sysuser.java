package com.xtc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="sys_user")
public class sysuser implements Serializable {
		@Id
		@GeneratedValue(generator="uuid_")
		@GenericGenerator(name="uuid_",strategy="uuid")
		@Column(length=40)
		private String id;
		private String mobile;//varchar(11) DEFAULT NULL COMMENT '登录名',
		private String password;//varchar(50) DEFAULT NULL COMMENT '密码',
		private String name;//varchar(20) DEFAULT NULL COMMENT '姓名',
		private String sex;//varchar(4) DEFAULT NULL COMMENT '性别',
		private String address;//varchar(200) DEFAULT NULL COMMENT '地址',
		private String email;//varchar(30) DEFAULT NULL COMMENT '邮箱',
		private String age;//String(11) DEFAULT NULL COMMENT '年龄',
		private String idCard;//varchar(18) DEFAULT NULL COMMENT '身份证',
		private String nationality;//varchar(30) DEFAULT NULL COMMENT '国籍',
		private Date birth_day;//date DEFAULT NULL COMMENT '出生日期',
		private String avatar;//longtext COMMENT '头像',
		private String deviceId;//varchar(300) DEFAULT NULL COMMENT '终端id(拼接)',
		private String is_driver;//varchar(1) DEFAULT '0' COMMENT '是否为驾驶员',
		private String is_proerty_manager;//varchar(1) DEFAULT '0' COMMENT '是否为物业经理',
		private String is_daibo;//varchar(1) DEFAULT '0' COMMENT '是否代泊',
		private String is_guard_manager;//varchar(1) DEFAULT '0' COMMENT '是否为保安经理',
		private String is_owner;//varchar(1) DEFAULT '0' COMMENT '是否为业主',
		private String is_casher;//varchar(1) DEFAULT '0' COMMENT '是否为收费员',
		private String is_guard;//varchar(1) DEFAULT '0' COMMENT '是否为保安',
		private String is_valid_mail;//varchar(1) DEFAULT '0' COMMENT '邮箱是否验证（0：未验证 1：验证）',
		private String status;//varchar(1) DEFAULT NULL COMMENT '账号状态',
		private Date date_created;//datetime DEFAULT NULL COMMENT '注册时间',
		private Date date_updated;//datetime DEFAULT NULL COMMENT '更新时间',
		private String auth_code;//varchar(10) DEFAULT NULL COMMENT '验证码',
		private String idcard_front;//longtext COMMENT '身份证正面',
		private String idcard_back;//longtext COMMENT '身份证反面',
		private String is_admin;//varchar(1) DEFAULT NULL COMMENT '是否有管理员权限',
		private String is_operater;
		private String parkId;
		private Date driving_licence_register_date;
		private String accid;//	
		private String invideCode;//邀请码
		private String first;//'第一次登录';
		private String property;//'物业';
		private String isdelete;//删除状态 0:未删除，1已删除';
		private Date prousertime;//'创建时间'; 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		public Date getBirth_day() {
			return birth_day;
		}
		public void setBirth_day(Date birth_day) {
			this.birth_day = birth_day;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public String getIs_driver() {
			return is_driver;
		}
		public void setIs_driver(String is_driver) {
			this.is_driver = is_driver;
		}
		public String getIs_proerty_manager() {
			return is_proerty_manager;
		}
		public void setIs_proerty_manager(String is_proerty_manager) {
			this.is_proerty_manager = is_proerty_manager;
		}
		public String getIs_daibo() {
			return is_daibo;
		}
		public void setIs_daibo(String is_daibo) {
			this.is_daibo = is_daibo;
		}
		public String getIs_guard_manager() {
			return is_guard_manager;
		}
		public void setIs_guard_manager(String is_guard_manager) {
			this.is_guard_manager = is_guard_manager;
		}
		public String getIs_owner() {
			return is_owner;
		}
		public void setIs_owner(String is_owner) {
			this.is_owner = is_owner;
		}
		public String getIs_casher() {
			return is_casher;
		}
		public void setIs_casher(String is_casher) {
			this.is_casher = is_casher;
		}
		public String getIs_guard() {
			return is_guard;
		}
		public void setIs_guard(String is_guard) {
			this.is_guard = is_guard;
		}
		public String getIs_valid_mail() {
			return is_valid_mail;
		}
		public void setIs_valid_mail(String is_valid_mail) {
			this.is_valid_mail = is_valid_mail;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getDate_created() {
			return date_created;
		}
		public void setDate_created(Date date_created) {
			this.date_created = date_created;
		}
		public Date getDate_updated() {
			return date_updated;
		}
		public void setDate_updated(Date date_updated) {
			this.date_updated = date_updated;
		}
		public String getAuth_code() {
			return auth_code;
		}
		public void setAuth_code(String auth_code) {
			this.auth_code = auth_code;
		}
		public String getIdcard_front() {
			return idcard_front;
		}
		public void setIdcard_front(String idcard_front) {
			this.idcard_front = idcard_front;
		}
		public String getIdcard_back() {
			return idcard_back;
		}
		public void setIdcard_back(String idcard_back) {
			this.idcard_back = idcard_back;
		}
		public String getIs_admin() {
			return is_admin;
		}
		public void setIs_admin(String is_admin) {
			this.is_admin = is_admin;
		}
		public String getIs_operater() {
			return is_operater;
		}
		public void setIs_operater(String is_operater) {
			this.is_operater = is_operater;
		}
		public String getParkId() {
			return parkId;
		}
		public void setParkId(String parkId) {
			this.parkId = parkId;
		}
		public Date getDriving_licence_register_date() {
			return driving_licence_register_date;
		}
		public void setDriving_licence_register_date(Date driving_licence_register_date) {
			this.driving_licence_register_date = driving_licence_register_date;
		}
		public String getAccid() {
			return accid;
		}
		public void setAccid(String accid) {
			this.accid = accid;
		}
		public String getInvideCode() {
			return invideCode;
		}
		public void setInvideCode(String invideCode) {
			this.invideCode = invideCode;
		}
		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getIsdelete() {
			return isdelete;
		}
		public void setIsdelete(String isdelete) {
			this.isdelete = isdelete;
		}
		public Date getProusertime() {
			return prousertime;
		}
		public void setProusertime(Date prousertime) {
			this.prousertime = prousertime;
		}
		public sysuser() {
		}
		public sysuser(String id, String mobile, String password, String name, String sex, String address, String email,
				String age, String idCard, String nationality, Date birth_day, String avatar, String deviceId,
				String is_driver, String is_proerty_manager, String is_daibo, String is_guard_manager, String is_owner,
				String is_casher, String is_guard, String is_valid_mail, String status, Date date_created,
				Date date_updated, String auth_code, String idcard_front, String idcard_back, String is_admin,
				String is_operater, String parkId, Date driving_licence_register_date, String accid, String invideCode,
				String first, String property, String isdelete, Date prousertime) {
			this.id = id;
			this.mobile = mobile;
			this.password = password;
			this.name = name;
			this.sex = sex;
			this.address = address;
			this.email = email;
			this.age = age;
			this.idCard = idCard;
			this.nationality = nationality;
			this.birth_day = birth_day;
			this.avatar = avatar;
			this.deviceId = deviceId;
			this.is_driver = is_driver;
			this.is_proerty_manager = is_proerty_manager;
			this.is_daibo = is_daibo;
			this.is_guard_manager = is_guard_manager;
			this.is_owner = is_owner;
			this.is_casher = is_casher;
			this.is_guard = is_guard;
			this.is_valid_mail = is_valid_mail;
			this.status = status;
			this.date_created = date_created;
			this.date_updated = date_updated;
			this.auth_code = auth_code;
			this.idcard_front = idcard_front;
			this.idcard_back = idcard_back;
			this.is_admin = is_admin;
			this.is_operater = is_operater;
			this.parkId = parkId;
			this.driving_licence_register_date = driving_licence_register_date;
			this.accid = accid;
			this.invideCode = invideCode;
			this.first = first;
			this.property = property;
			this.isdelete = isdelete;
			this.prousertime = prousertime;
		}
}
