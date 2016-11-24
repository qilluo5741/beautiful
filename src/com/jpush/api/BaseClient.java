package com.jpush.api;

import java.util.HashSet;
import java.util.Set;

public class BaseClient {
	protected String masterSecret = "abdc91fcc99cd38b19a4e9c3";
	protected String appKey = "02598c822dede2bb0f4bac2c";
	protected String sendDescription = "batp停车";//发送的描述
	protected long timeToLive = -1; //保存离线的时长。秒为单位。默认为保存1天的离线消息
	protected boolean enableSSL = false;
	protected Set<DeviceEnum> devices = new HashSet<DeviceEnum>();//默认发送android和ios

	public String getMasterSecret() {
		return masterSecret;
	}

	public long getTimeToLive() {
		return timeToLive;
	}

	protected String getAppKey() {
		return this.appKey;
	}

	public void setSendDescription(String description) {
		this.sendDescription = description;
	}

	protected String getSendDescription() {
		return this.sendDescription;
	}

	protected Set<DeviceEnum> getDevices() {
		if (null == this.devices) {
			this.devices = new HashSet<DeviceEnum>();
		}
		if (this.devices.size() == 0) {
			this.devices.add(DeviceEnum.Android);
			this.devices.add(DeviceEnum.IOS);
		}
		return this.devices;
	}

	/*
	 * @description 是否使用ssl安全连接
	 */
	public void setEnableSSL(boolean enableSSL) {
		this.enableSSL = enableSSL;
	}
}
