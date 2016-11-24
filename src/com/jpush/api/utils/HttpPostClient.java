package com.jpush.api.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;

import com.jpush.api.BaseURL;
import com.jpush.api.CustomMessageParams;
import com.jpush.api.MessageParams;
import com.jpush.api.MessageResult;
import com.jpush.api.MsgTypeEnum;
import com.jpush.api.NotifyMessageParams;
import com.jpush.api.SimpleHostnameVerifier;
import com.jpush.api.SimpleTrustManager;



public class HttpPostClient {

	private final String CHARSET = "UTF-8";
	//设置连接超时时间
	private final int DEFAULT_CONNECTION_TIMEOUT = (20 * 1000);
	//设置读取超时时间
	private final int DEFAULT_SOCKET_TIMEOUT = (30 * 1000);

	public  MessageResult post(final String path, final boolean enableSSL, final MessageParams messageParams) {
		byte[] data = null;
		try {
			data = parse(messageParams).getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sendPost(path, enableSSL, data);
	}

	public  MessageResult simplePost(final String path, final boolean enableSSL, final MessageParams messageParams) {
		byte[] data = null;
		try {
			data = simpleParse(messageParams).getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sendPost(path,enableSSL,data);
	}

	protected MessageResult sendPost(final String path, final boolean enableSSL, byte[] data){
		HttpURLConnection conn = null;
		DataOutputStream outStream = null;
		MessageResult messageResult = null;
		try {
			if (enableSSL) {
				initSSL();
			}
			
			URL url = new URL(BaseURL.getUrlForPath(path, enableSSL));	
			//System.out.println(BaseURL.getUrlForPath(path, enableSSL));
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
			conn.setReadTimeout(DEFAULT_SOCKET_TIMEOUT);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", CHARSET);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			outStream = new DataOutputStream(conn.getOutputStream());
			outStream.write(data);
			outStream.flush();

			if (conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				StringBuffer sb = new StringBuffer();
				InputStreamReader reader = new InputStreamReader(in, CHARSET);
				char[] buff = new char[1024];
				int len;
				while ((len = reader.read(buff)) > 0) {
					sb.append(buff, 0, len);
				}
			//	System.out.println("send params = "+sb.toString());
				if(!"".equals(sb.toString())){
					messageResult = MessageResult.fromValue(sb.toString());
				}
			} else {
				throw new Exception("ResponseCode=" + conn.getResponseCode());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != outStream) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
		return messageResult;
	}
	protected void initSSL() {
		try {
			TrustManager[] tmCerts = new javax.net.ssl.TrustManager[1];
			tmCerts[0] = new SimpleTrustManager();
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, tmCerts, null);
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier hv = new SimpleHostnameVerifier();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String parse(MessageParams message) { 
		String input = String.valueOf(message.getSendNo()) + message.getReceiverType().value() + message.getReceiverValue() + message.getMasterSecret();
		int msgType = 0;
		if (message instanceof NotifyMessageParams) {
			msgType = MsgTypeEnum.NOTIFY.value();
		} else if (message instanceof CustomMessageParams)  {
			msgType = MsgTypeEnum.CUSTOM.value();
		}

		Map<String, String> nvPair = new HashMap<String, String> ();
		nvPair.put("sendno", message.getSendNo());
		nvPair.put("app_key", message.getAppKey());
		nvPair.put("receiver_type", String.valueOf(message.getReceiverType().value()));
		nvPair.put("receiver_value", message.getReceiverValue());
		nvPair.put("verification_code", StringUtils.toMD5(input));
		nvPair.put("msg_type", String.valueOf(msgType));
		nvPair.put("msg_content", message.getMsgContent().toString());
		nvPair.put("send_description", message.getSendDescription());
		nvPair.put("platform", message.getPlatform());
		if(message.getTimeToLive() >=0) 
			nvPair.put("time_to_live", String.valueOf(message.getTimeToLive()));
	
		return mapWithParms(nvPair);
	}

	public String simpleParse(MessageParams message){
		String input = String.valueOf(message.getSendNo()) + message.getReceiverType().value() + message.getReceiverValue() + message.getMasterSecret();
		Map<String, String> nvPair = new HashMap<String, String>();
		nvPair.put("sendno", message.getSendNo());
		nvPair.put("app_key", message.getAppKey());
		nvPair.put("receiver_type", String.valueOf(message.getReceiverType().value()));
		nvPair.put("receiver_value", message.getReceiverValue());
		nvPair.put("verification_code", StringUtils.toMD5(input));
		nvPair.put("txt", message.getTxt());
		nvPair.put("platform", message.getPlatform());
		if(message.getTimeToLive() >=0) 
			nvPair.put("time_to_live", String.valueOf(message.getTimeToLive()));

		return mapWithParms(nvPair);
	}

	protected String mapWithParms(Map<String, String> nvPair){
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : nvPair.entrySet()) {
			builder.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		return builder.toString();
	}
	/**
	 * post请求
	 * @param httpUrl
	 * @param httpArg
	 * @return
	 */
	public static String request_post(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
