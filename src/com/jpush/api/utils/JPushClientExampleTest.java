package com.jpush.api.utils;


import com.jpush.api.JPushClient;

public class JPushClientExampleTest {
		//在极光注册上传应用的 appKey 和 masterSecret
		private static final String appKey ="02598c822dede2bb0f4bac2c";////必填，例如466f7032ac604e02fb7bda89
		private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//必填，每个应用都对应一个masterSecret
		private static JPushClient jpush = null;
		/*
		 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
		 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
		 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒
		 */
		private static long timeToLive =  60 * 5;  

		public static void main(String[] args) {
			jpush = new JPushClient(masterSecret,appKey,timeToLive);
	 		jpush.setEnableSSL(true);//执行推送
			/* 
			 * 是否启用ssl安全连接, 可选
			 * 参数：启用true， 禁用false，默认为非ssl连接
			 */
	 			/*Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyHmmd");
				String time=format.format(date);
			    String sendNo="4219527501";*/
			//jpush.setEnableSSL(true);
			//测试发送消息或者通知
			//testSend();
	 		//testSendByRegId();
		}
		/*private static void testSendByRegId(){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyHmmd");
			String time=format.format(date);
			jpush.sendRegIdMessage(time,appKey,masterSecret,"【BATP停车】！！！！","170976fa8a83f73a1ed");
		}*/
		/*private static void testSend() {
		    //在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
		    //除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
			//Integer num= getRandomSendNo(); d 3
		    String sendNo="1900192560";
			String msgTitle = "jpush,TEST\"\"";
			String msgContent = "好消息！好消息！";
			 * IOS设备扩展参数,
			 * 设置badge，设置声音
			Map<String, Object> extra = new HashMap<String, Object>();
			IOSExtra iosExtra = new IOSExtra(1, "WindowsLogonSound.wav");
			extra.put("ios", iosExtra);
			//IOS和安卓一起
			MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, 0, extra);
			//MessageResult msgResult =jpush.sendNotificationWithTag("1244354", "11111", "我是标题", "我是消息内容");
			//MessageResult msgResult =jpush.sendNotificationWithAlias("1234544", "123", "aaa", "aaaaaaaa");
			//对所有用户发送通知, 更多方法请参考文档
			//MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
			if (null != msgResult) {
				System.out.println("服务器返回数据: " + msgResult.toString());
				if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
					System.out.println("发送成功， sendNo=" + msgResult.getSendno());
				} else {
					System.out.println("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg());
				} 
			} else {
				System.out.println("无法获取数据");
			}
		}*/
		
	    public static final int MAX = Integer.MAX_VALUE;
		public static final int MIN = (int) MAX/2;
		
		/**
		 * 保持 sendNo 的唯一性是有必要的
		 * It is very important to keep sendNo unique.
		 * @return sendNo
		 */
		public static int getRandomSendNo() {
		    return (int) (MIN + Math.random() * (MAX - MIN));
		}
}
