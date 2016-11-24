package com.xtc.util;
import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;
/**
 * 防止xxl，sql注入
 *
 */
public class StringEscapeEditor extends PropertyEditorSupport {
	/**
	 * 使用方法  在Controller中放进该代码
	 */
	private boolean escapeHTML;
	private boolean escapeJavaScript;
	private boolean escapeSQL;
	public boolean isEscapeHTML() {
		return escapeHTML;
	}
	public void setEscapeHTML(boolean escapeHTML) {
		this.escapeHTML = escapeHTML;
	}
	public boolean isEscapeJavaScript() {
		return escapeJavaScript;
	}
	public void setEscapeJavaScript(boolean escapeJavaScript) {
		this.escapeJavaScript = escapeJavaScript;
	}
	public boolean isEscapeSQL() {
		return escapeSQL;
	}
	public void setEscapeSQL(boolean escapeSQL) {
		this.escapeSQL = escapeSQL;
	}
	public StringEscapeEditor() {
		super();
	}
	
	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript,
			boolean escapeSQL) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
		this.escapeSQL = escapeSQL;
	}
	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} 
		else {
			String value = text;
			if (escapeHTML) {  
				value = StringEscapeUtils.escapeHtml(value); 
			}
			if (escapeJavaScript) {   
				value = StringEscapeUtils.escapeJavaScript(value); 
			}
			if (escapeSQL) {    
				value = StringEscapeUtils.escapeSql(value);   
			}   
			setValue(value);   
		}
	}
	@Override
	public String getAsText() {
		 Object value = getValue();    
		 return value != null ? value.toString() : ""; 
	}
}
