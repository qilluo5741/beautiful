package com.xtc.util;

import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
public class SmsSendClientExample {

	public static void sendMessage(String mobile,String Plate){
	try 
		  { 
			String tp="����Ϊ@���û�ԤԼ������ͣ��λ!�뵽BATPƽ̨������������ӭ����ʹ�á���BATPͣ����";
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
				String tp="���Ѿ�ԤԼ�ɹ��������Ѹ���Ԥ��λ�ã�ף��ͣ�����!��BATPͣ����";
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
			String tp="�Բ�����Ķ����ѱ��ܾ������ԤԼ�ѽ���������֮�ڵ����������˻�!��BATPͣ����";
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
			String tp="�𾴵����û����㵱ǰ��һ��ͣ������δ֧������ȥ��ͣ���ɷѡ���֧�����ã���л����ʹ�ã���BATPͣ����";
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
		sendchargeMessage("15836193514","��A74110");
	}
}
