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
@Table(name="tag")
public class Tag implements Serializable{
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	    private String      id         ;
		private  String 	name	            ;
		private  String 	userId	            ;
		private  String 	date_updated	    ;
		private  String 	type	            ;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getDate_updated() {
			return date_updated;
		}
		public void setDate_updated(String date_updated) {
			this.date_updated = date_updated;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Tag(String id, String name, String userId, String date_updated,
				String type) {
			super();
			this.id = id;
			this.name = name;
			this.userId = userId;
			this.date_updated = date_updated;
			this.type = type;
		}
		public Tag() {
			super();
		}
		
		

}
