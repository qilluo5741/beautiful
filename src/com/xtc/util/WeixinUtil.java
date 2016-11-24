package com.xtc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WeixinUtil {
	/***
	  * 将文本消息对象转为xml
	  * @param text
	  * @return
	  */
	public static String toXml(Object text){
		XStream xs=new XStream(new XppDriver(new XmlFriendlyReplacer("_-","_")));//解决解析下划线时出现双下划线
		xs.alias("xml",text.getClass());
		return xs.toXML(text);
	}
	/***
	 * 将xml转换为map集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(HttpServletRequest request){
		Map<String, String> map=null;
		try {
			map = new TreeMap<String, String>();
			SAXReader sr=new SAXReader();
			InputStream ins=request.getInputStream();//从request中获取输入流
			Document doc=sr.read(ins);
			Element  root=doc.getRootElement();
			List<Element> list=root.elements();
			//遍历 保存集合
			for(Element e:list){
				map.put(e.getName(), e.getText());
			}
			ins.close();
		} catch (IOException e) {
			Log.getInstance().debug("微信通知/请求转换失败！1");
		} catch (DocumentException e) {
			Log.getInstance().debug("微信通知/请求转换失败！2");
		}
		return map;
	}
}