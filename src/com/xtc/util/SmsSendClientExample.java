package com.xtc.util;

import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
public class SmsSendClientExample {

	public static void sendMessage(String mobile,String Plate){
	try 
		  { 
			String tp="车牌为@的用户预约了您的停车位!请到BATP平台处理订单！，欢迎您的使用。【BATP停车】";
			String result = IOUtils.toString(
			new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
	        + "&password=xiangbo558" + "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(tp.replace("@",Plate), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
			System.out.println(result);
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	}
	public static void sendUserMessage(String mobile,String authCode){
		try 
			  { 
				String tp="你已经预约成功！保安已给您预留位置，祝你停车愉快!【BATP停车】";
				String result = IOUtils.toString(
				new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
		        + "&password=xiangbo558" + "&mobile=" + 
		        mobile + "&content=" + 
		        URLEncoder.encode(tp.replace("@",authCode), "utf-8"))
		        .openConnection().getInputStream(), "utf-8");
				System.out.println(result);
		    }catch (Exception e){
		      throw new RuntimeException("Sms Send Error Caused.", e);
		    }
	}
	public static void sendisMessage(String mobile,String authCode){
		try 
		  { 
			String tp="对不起！你的订单已被拒绝！你的预约费将在三分钟之内到达你的余额账户!【BATP停车】";
			String result = IOUtils.toString(
			new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
	        + "&password=xiangbo558" + "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(tp.replace("@",authCode), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
			System.out.println(result);
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	}
	public static void sendchargeMessage(String mobile,String Plate){
		try 
		  { 
			String tp="尊敬的享泊用户，你当前有一笔停车费用未支付！请去“停车缴费”中支付费用，感谢您的使用！【BATP停车】";
			String result = IOUtils.toString(
			new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
	        + "&password=xiangbo558" + "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(tp.replace("@",Plate), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
			System.out.println(result);
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
		}
	public static void main(String[] args) {
		sendchargeMessage("15836193514","渝A74110");
	}
}
