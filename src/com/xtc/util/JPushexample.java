package com.xtc.util;

import org.apache.commons.lang.RandomStringUtils;

import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
//推送调用
public class JPushexample {
    //在极光注册上传应用的 appKey 和 masterSecret
	private static final String appKey ="e29eee088d344f38703670df";////必填，例如466f7032ac604e02fb7bda89
	
	private static final String masterSecret = "15b1ef18c6644e74c476c069";//必填，每个应用都对应一个masterSecret
	
	private static JPushClient jpush = null;
	/*
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒
	 */
	private static long timeToLive =  60 * 5;
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	//ios的调用发法
	public static void sendiosRegIdMessage(String Plate,String regId){
		jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
		/* 
		 * 是否启用ssl安全连接, 可选
		 * 参数：启用true， 禁用false，默认为非ssl连接
		 */
		jpush.setEnableSSL(true);
		String sendNo = RandomStringUtils.randomNumeric(1);
		String urts="车牌为"+Plate+"的用户预约了您的停车场!";//推送的内容
		jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,urts,regId);
	}
	//Android的调用发法
	public static void sendandroidRegIdMessage(String out_trade_no,String Plate,String regId){
		jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
		/* 
		 * 是否启用ssl安全连接, 可选
		 * 参数：启用true， 禁用false，默认为非ssl连接
		 */
		jpush.setEnableSSL(true);
		String sendNo = RandomStringUtils.randomNumeric(10);
		String urt="车牌为"+Plate+"的用户预约了您的停车场!="+out_trade_no;//推送的内容
		jpush.sendRegIdMessage(sendNo,appKey,masterSecret,urt,regId);
	}
	
	//Android的调用
	public static void sendandroidRegIdMessages(String regId){
		jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
		/* 
		 * 是否启用ssl安全连接, 可选
		 * 参数：启用true， 禁用false，默认为非ssl连接
		 */
		jpush.setEnableSSL(true);
		String sendNo = RandomStringUtils.randomNumeric(10);
		String urt="您好！你的订单未被接受,自动退回来了！";//推送的内容
		jpush.sendRegIdMessagess(sendNo,appKey,masterSecret,urt,regId);
	}
}
