package com.xtc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;

public class CreateCode {

	public static void main(String[] args) {
		String auth = RandomStringUtils.randomNumeric(10);//随机数
		System.out.println(auth);
		String psw = MD5Util.encode("123456");
		System.out.println(psw);
		String inviteCode = getRandomChar();
		System.out.println(inviteCode);
	}
	//生成六位验证码
	public static String getRandomChar(){
		String randChar = "";
		for (int i = 0; i < 6; i++) {
			int index = (int) Math.round(Math.random() * 1);
			switch (index) {
			case 0:// 大写字符
				randChar += String
						.valueOf((char) Math.round(Math.random() * 25 + 65));
				break;
			default:// 数字
				randChar += String.valueOf(Math.round(Math.random() * 9));
				break;
			}
		}
		return randChar;
	}
	public static double sendshare(){
		Random r = new Random(); 
		double d2 = r.nextDouble() * 1.9;//随机数1到2.9
		String wqe=(d2+1)+"";
		double d4=Double.valueOf(wqe.substring(0,wqe.indexOf(".")+2));
		return d4;
	}
	public static String shareend(){
		long currentTime = System.currentTimeMillis() ;
		currentTime +=4320*60*1000;//时间加三天
		Date date=new Date(currentTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}
}