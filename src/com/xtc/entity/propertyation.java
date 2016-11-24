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
@Table(name="propertyation")
public class propertyation implements Serializable{
		@Id
		@GeneratedValue(generator="uuid_")
		@GenericGenerator(name="uuid_",strategy="uuid")
		@Column(length=40)
		private String propertyid;
		private String propertyAddress;
		private String parkid;
		private String markid;
		private Date propertytime;
		private String propertyphone;
		public String getPropertyid() {
			return propertyid;
		}
		public void setPropertyid(String propertyid) {
			this.propertyid = propertyid;
		}
		public String getPropertyAddress() {
			return propertyAddress;
		}
		public void setPropertyAddress(String propertyAddress) {
			this.propertyAddress = propertyAddress;
		}
		public String getParkid() {
			return parkid;
		}
		public void setParkid(String parkid) {
			this.parkid = parkid;
		}
		public String getMarkid() {
			return markid;
		}
		public void setMarkid(String markid) {
			this.markid = markid;
		}
		public Date getPropertytime() {
			return propertytime;
		}
		public void setPropertytime(Date propertytime) {
			this.propertytime = propertytime;
		}
		public String getPropertyphone() {
			return propertyphone;
		}
		public void setPropertyphone(String propertyphone) {
			this.propertyphone = propertyphone;
		}
		public propertyation() {
		}
		public propertyation(String propertyid, String propertyAddress, String parkid, String markid, Date propertytime,String propertyphone) {
			this.propertyid = propertyid;
			this.propertyAddress = propertyAddress;
			this.parkid = parkid;
			this.markid = markid;
			this.propertytime = propertytime;
			this.propertyphone = propertyphone;
		}
}
