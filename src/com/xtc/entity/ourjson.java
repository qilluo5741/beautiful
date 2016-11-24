package com.xtc.entity;

public class ourjson {
	private String id;
	private String ContentType;
	private String Content;
	private Object extlsit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContentType() {
		return ContentType;
	}
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Object getExtlsit() {
		return extlsit;
	}
	public void setExtlsit(Object extlsit) {
		this.extlsit = extlsit;
	}
	public ourjson() {
	}
	public ourjson(String id, String contentType,String content, Object extlsit) {
		this.id = id;
		this.ContentType = contentType;
		this.Content = content;
		this.extlsit = extlsit;
	}
}
