package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author Administrator
 *
 */
@Entity
public class TestInfo {
	
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String id;
	private String name;
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
	public TestInfo() {
		super();
	}
	public TestInfo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TestInfo(String name) {
		super();
		this.name = name;
	}
	
	
	
}