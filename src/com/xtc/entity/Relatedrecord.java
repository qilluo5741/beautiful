package com.xtc.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
/**
 * 物业关联信息
 * @author Administrator
 *
 */
@Entity
@Table(name="relatedrecord")
public class Relatedrecord {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private  String  recordid;
	private  String  recordphone;//'市场人手机',
	private  String  relatedphone;//'物业手机',
	private  Date  recordtime;//关联时间',
	private  String  parkid;//停车场id'
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getRecordphone() {
		return recordphone;
	}
	public void setRecordphone(String recordphone) {
		this.recordphone = recordphone;
	}
	public String getRelatedphone() {
		return relatedphone;
	}
	public void setRelatedphone(String relatedphone) {
		this.relatedphone = relatedphone;
	}
	public Date getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public Relatedrecord() {
	}
	public Relatedrecord(String recordid, String recordphone, String relatedphone, Date recordtime, String parkid) {
		this.recordid = recordid;
		this.recordphone = recordphone;
		this.relatedphone = relatedphone;
		this.recordtime = recordtime;
		this.parkid = parkid;
	}
}
