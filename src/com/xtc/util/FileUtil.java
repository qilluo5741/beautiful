package com.xtc.util;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.core.util.Base64Encoder;

/**
 * �ļ�д��
 *
 */
public class FileUtil {
	/***
	 * �����ļ� д�뱾��
	 * @param fileCode ����base64���ܵ��ַ���
	 * @param req HttpServletRequest����
	 * @param filefolder ����ͼƬ���ļ���
	 * @return ͼƬ����  ͨ��uuid����
	 */
	public static String uploadImg(String fileCode,HttpServletRequest req,String filefolder){
		//��Ŀ��Ŀ¼
		String path=req.getSession().getServletContext().getRealPath("/")+filefolder+"\\";
		//��֤�ļ����Ƿ���ڣ����ھʹ���
		File f=new File(path);
		if(!f.exists()){
			f.mkdir();//����
		}
		//д���ļ�
		String imgName=UUID.randomUUID().toString()+".jpg";
		 try {
			byte[] buffer = new Base64Encoder().decode(fileCode);
			FileOutputStream out = new FileOutputStream(path+"\\"+imgName);
		    out.write(buffer);
		    out.close();
		} catch (Exception e) {
			return  null;
		}
		return imgName;
	}

}
