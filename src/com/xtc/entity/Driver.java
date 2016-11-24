package com.xtc.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * 驾驶认证?
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="sys_driver")
public class Driver implements Serializable {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	  private String id;
	  private String  userId	;
	  private String  driver_license	     ;
	  private String  vehicleid	         ;
	  private String  archive_num	         ;
	  private String  is_load	             ;
	  private String  licenced_time	     ;
	  private String  valid_start			;		
	  private String  valid_end	         ;     //有效期止		
	  private String  classs	             ;   //	准驾车
	  private String  load_time	         ;    //	上传时间			
	  private String  audit_time	         ;  //	审核时间			
	  private String  audit_status	     ;    //核状态(0：待审核 1：通过 2： 图片不符合标准 3：图片不清晰 4：无效证件)	
	  private String  driver_license_front;	 //驾驶证正面
	  private String  driver_license_back	 ;    //驾驶证反面	
	  private String  date_updated		;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDriver_license() {
		return driver_license;
	}
	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getArchive_num() {
		return archive_num;
	}
	public void setArchive_num(String archive_num) {
		this.archive_num = archive_num;
	}
	public String getIs_load() {
		return is_load;
	}
	public void setIs_load(String is_load) {
		this.is_load = is_load;
	}
	public String getLicenced_time() {
		return licenced_time;
	}
	public void setLicenced_time(String licenced_time) {
		this.licenced_time = licenced_time;
	}
	public String getValid_start() {
		return valid_start;
	}
	public void setValid_start(String valid_start) {
		this.valid_start = valid_start;
	}
	public String getValid_end() {
		return valid_end;
	}
	public void setValid_end(String valid_end) {
		this.valid_end = valid_end;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getLoad_time() {
		return load_time;
	}
	public void setLoad_time(String load_time) {
		this.load_time = load_time;
	}
	public String getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	public String getDriver_license_front() {
		return driver_license_front;
	}
	public void setDriver_license_front(String driver_license_front) {
		this.driver_license_front = driver_license_front;
	}
	public String getDriver_license_back() {
		return driver_license_back;
	}
	public void setDriver_license_back(String driver_license_back) {
		this.driver_license_back = driver_license_back;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public Driver(String id, String userId, String driver_license,
			String vehicleid, String archive_num, String is_load,
			String licenced_time, String valid_start, String valid_end,
			String classs, String load_time, String audit_time,
			String audit_status, String driver_license_front,
			String driver_license_back, String date_updated) {
		this.id = id;
		this.userId = userId;
		this.driver_license = driver_license;
		this.vehicleid = vehicleid;
		this.archive_num = archive_num;
		this.is_load = is_load;
		this.licenced_time = licenced_time;
		this.valid_start = valid_start;
		this.valid_end = valid_end;
		this.classs = classs;
		this.load_time = load_time;
		this.audit_time = audit_time;
		this.audit_status = audit_status;
		this.driver_license_front = driver_license_front;
		this.driver_license_back = driver_license_back;
		this.date_updated = date_updated;
	}
	public Driver() {
	}
}
