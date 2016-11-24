package com.xtc.util;

import org.apache.log4j.Logger;

public class LogUtil {
	//定义一个日志记录器
	private Logger logger = null;
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public LogUtil() {
		//初始化日志文件
		String filePath = this.getClass().getResource("/").getPath();
		filePath = filePath.substring(1);
		//组装配置信息文件路径
		filePath = filePath+"log4j.properties";
		//实例化日志记录器
		this.logger = Logger.getLogger(this.getClass());
	}
}
