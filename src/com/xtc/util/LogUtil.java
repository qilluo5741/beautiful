package com.xtc.util;

import org.apache.log4j.Logger;

public class LogUtil {
	//����һ����־��¼��
	private Logger logger = null;
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public LogUtil() {
		//��ʼ����־�ļ�
		String filePath = this.getClass().getResource("/").getPath();
		filePath = filePath.substring(1);
		//��װ������Ϣ�ļ�·��
		filePath = filePath+"log4j.properties";
		//ʵ������־��¼��
		this.logger = Logger.getLogger(this.getClass());
	}
}
