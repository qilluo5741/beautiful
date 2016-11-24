package com.jpush.api;

import java.util.Map;

/**
 * The entrance of JPush API library.
 *
 */
public class JPushClient extends BaseClient {

	protected static HttpPostClient httpClient = new HttpPostClient();

	public JPushClient(String masterSecret, String appKey) {
		this.masterSecret = masterSecret;
		this.appKey = appKey;
	}
	
	public JPushClient(String masterSecret, String appKey, long timeToLive) {
		this.masterSecret = masterSecret;
		this.appKey = appKey;
		this.timeToLive = timeToLive;
	}

	public JPushClient(String masterSecret, String appKey, DeviceEnum device) {
		this.masterSecret = masterSecret;
		this.appKey = appKey;
		devices.add(device);
	}

	public JPushClient(String masterSecret, String appKey, long timeToLive, DeviceEnum device) {
		this.masterSecret = masterSecret;
		this.appKey = appKey;
		this.timeToLive = timeToLive;
		this.devices.add(device);
	}

	/*
	 * @description 发送带IMEI的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithImei(String sendNo, String imei, String msgTitle, String msgContent) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.IMEI);
		p.setReceiverValue(imei);
		return sendNotification(p, sendNo, msgTitle, msgContent, 0, null);
	}

	/*
	 * @params builderId通知栏样式
	 * @description 发送带IMEI的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithImei(String sendNo, String imei, String msgTitle, String msgContent, int builderId, Map<String, Object> extra) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.IMEI);
		p.setReceiverValue(imei);
		return sendNotification(p, sendNo, msgTitle, msgContent, builderId, extra);
	}

	/*
	 * @description 发送带IMEI的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithImei(String sendNo, String imei, String msgTitle, String msgContent) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.IMEI);
		p.setReceiverValue(imei);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, null, null);
	}

	/*
	 * @params msgContentType消息的类型，extra附属JSON信息
	 * @description 发送带IMEI的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithImei(String sendNo, String imei, String msgTitle, String msgContent, String msgContentType, Map<String, Object> extra) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.IMEI);
		p.setReceiverValue(imei);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, msgContentType, extra);
	}

	/*
	 * @description 发送带TAG的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithTag(String sendNo, String tag, String msgTitle, String msgContent) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.TAG);
		p.setReceiverValue(tag);
		return sendNotification(p, sendNo, msgTitle, msgContent, 0, null);
	}

	/*
	 * @params builderId通知栏样式
	 * @description 发送带TAG的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithTag(String sendNo, String tag, String msgTitle, String msgContent, int builderId, Map<String, Object> extra) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.TAG);
		p.setReceiverValue(tag);
		return sendNotification(p, sendNo, msgTitle, msgContent, builderId, extra);
	}

	/*
	 * @description 发送带TAG的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithTag(String sendNo, String tag, String msgTitle, String msgContent) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.TAG);
		p.setReceiverValue(tag);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, null, null);
	}

	/*
	 * @params msgContentType消息的类型，extra附属JSON信息
	 * @description 发送带TAG的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithTag(String sendNo, String tag, String msgTitle, String msgContent, String msgContentType, Map<String, Object> extra) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.TAG);
		p.setReceiverValue(tag);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, msgContentType, extra);
	}

	/*
	 * @description 发送带ALIAS的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithAlias(String sendNo, String alias, String msgTitle, String msgContent) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.ALIAS);
		p.setReceiverValue(alias);
		return sendNotification(p, sendNo, msgTitle, msgContent, 0, null);
	}

	/*
	 * @params builderId通知栏样式
	 * @description 发送带ALIAS的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithAlias(String sendNo, String alias, String msgTitle, String msgContent, int builderId, Map<String, Object> extra) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.ALIAS);
		p.setReceiverValue(alias);
		return sendNotification(p, sendNo, msgTitle, msgContent, builderId, extra);
	}

	/*
	 * @description 发送带ALIAS的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithAlias(String sendNo, String alias, String msgTitle, String msgContent) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.ALIAS);
		p.setReceiverValue(alias);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, null, null);
	}

	/*
	 * @params msgContentType消息的类型，extra附属JSON信息
	 * @description 发送带ALIAS的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithAlias(String sendNo, String alias, String msgTitle, String msgContent, String msgContentType, Map<String, Object> extra) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.ALIAS);
		p.setReceiverValue(alias);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, msgContentType, extra);
	}

	/*
	 * @description 发送带AppKey的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithAppKey(String sendNo, String msgTitle, String msgContent) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.APPKEYS);
		return sendNotification(p, sendNo, msgTitle, msgContent, 0, null);
	}

	/*
	 * @params builderId通知栏样式
	 * @description 发送带AppKey的通知
	 * @return MessageResult
	 */
	public MessageResult sendNotificationWithAppKey(String sendNo, String msgTitle, String msgContent, int builderId, Map<String, Object> extra) {
		NotifyMessageParams p = new NotifyMessageParams();
		p.setReceiverType(ReceiverTypeEnum.APPKEYS);
		return sendNotification(p, sendNo, msgTitle, msgContent, builderId, extra);
	}
	
	/*
	 * @description 发送带AppKey的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithAppKey(String sendNo, String msgTitle, String msgContent) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.APPKEYS);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, null, null);
	}

	/*
	 * @params msgContentType消息的类型，extra附属JSON信息
	 * @description 发送带AppKey的自定义消息
	 * @return MessageResult
	 */
	public MessageResult sendCustomMessageWithAppKey(String sendNo, String msgTitle, String msgContent, String msgContentType, Map<String, Object> extra) {
		CustomMessageParams p = new CustomMessageParams();
		p.setReceiverType(ReceiverTypeEnum.APPKEYS);
		return sendCustomMessage(p, sendNo, msgTitle, msgContent, msgContentType, extra);
	}

	protected MessageResult sendCustomMessage(CustomMessageParams p, String sendNo, String msgTitle, String msgContent, String msgContentType, Map<String, Object> extra) {
		if (null != msgContentType) {
			p.getMsgContent().setContentType(msgContentType);
		}
		if (null != extra) {
			p.getMsgContent().setExtra(extra);
		}
		return sendMessage(p, sendNo, msgTitle, msgContent);
	}

	protected MessageResult sendNotification(NotifyMessageParams p, String sendNo, String msgTitle, String msgContent, int builderId, Map<String, Object> extra) {
		p.getMsgContent().setBuilderId(builderId);
		if (null != extra) {
			p.getMsgContent().setExtra(extra);
		}
		return sendMessage(p, sendNo, msgTitle, msgContent);
	}

	protected MessageResult sendMessage(MessageParams p,String sendNo, String msgTitle, String msgContent) {
		p.setSendNo(sendNo);
		p.setAppKey(this.getAppKey());
		p.setMasterSecret(this.masterSecret);
		p.setTimeToLive(this.timeToLive);
		p.setSendDescription(this.getSendDescription());
		for (DeviceEnum device : this.getDevices()) {
			p.addPlatform(device);
		}
		if (null != msgTitle) {
			p.getMsgContent().setTitle(msgTitle);
		}
		p.getMsgContent().setMessage(msgContent);

		return sendMessage(p);
	}

	protected MessageResult sendMessage(MessageParams params) {
		return httpClient.post(BaseURL.ALL_PATH, this.enableSSL, params);
	}
	/***
	 * 
	 * @param sendNo 发送编号（最大支持32位正整数(即 4294967295 )）。由开发者自己维护，用于开发者自己标识一次发送请求。
	 * @param appKey待发送的应用程序(appKey)，只能填一个。
	 * @param masterSecret 
	 * @param extra 消息内容
	 * @param regId 设备ID
	 * @return 
	 */
	/*public void sendRegIdMessage(String sendNo,String appKey,String masterSecret,String msgContent,String regId){//1104a89792aa335f571
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=2&msg_content={\"message\":\""+msgContent+"\"}&platform=android";
		//String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=1&msg_content={\"n_content\":\""+msgContent+"\"}&platform=android";
		System.out.println(httpArg);
		String res=com.sharebo.util.httpClient.HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg );
		System.out.println(res);
	}*/
	//消息
	//
	public void sendRegIdMessage(String sendNo,String appKey,String masterSecret,String extra,String regId){
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=2&msg_content={\"message\":\""+extra+"\"}&platform=android";
		System.out.println(httpArg);
		String res=HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg);
		System.out.println(res);
	}
	/*public void sendRegIdMessage(String sendNo,String appKey,String masterSecret,Map<String, Object> extra,String regId){
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=2&msg_content={\"message\":\""+extra+"\"}&platform=android";
		System.out.println(httpArg);
		String res=HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg);
		System.out.println(res);
	}*/
	/*//通知
	public void sendRegIdMessageTag(String sendNo,String appKey,String masterSecret,String msgContent,String regId){
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=1&msg_content={\"n_content\":\""+msgContent+"\"}&platform=android";
		System.out.println(httpArg);
		String res=HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg);
		System.out.println(res);
	}*/
	//通知
	public void sendRegIdMessageTags(String sendNo,String appKey,String masterSecret,String msgContent,String regId){
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=1&msg_content={\"n_content\":\""+msgContent+"\"}&platform=ios";
		System.out.println(httpArg);
		String res=HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg);
		System.out.println(res);
	}
	
	//通知
	public void sendRegIdMessagess(String sendNo,String appKey,String masterSecret,String msgContent,String regId){//1104a89792aa335f571
		String input = String.valueOf(sendNo) + 5 + regId + masterSecret;
		String verificationCode=StringUtils.toMD5(input);
		//String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=2&msg_content={\"message\":\""+msgContent+"\"}&platform=android";
		String httpArg="sendno="+sendNo+"&app_key="+appKey+"&receiver_type=5&receiver_value="+regId+"&verification_code="+verificationCode+"&msg_type=1&msg_content={\"n_content\":\""+msgContent+"\"}&platform=android";
		System.out.println(httpArg);
		String res=HttpPostClient.request_post(BaseURL.PUSH_URL,httpArg );
		System.out.println(res);
	}
}
