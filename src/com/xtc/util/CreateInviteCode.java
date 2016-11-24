package com.xtc.util;

/**
 * 生成六位邀请码
 * @author Administrator
 *
 */
public class CreateInviteCode {

	public static void main(String[] args) {
		String inviteCode = getRandomChar();
		System.out.println(inviteCode);
	}
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
}
