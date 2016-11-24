package com.xtc.controller;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.AlipayNotify;
import com.weixin.config.WeixinConfig;
import com.xtc.entity.WeixinResult;
import com.xtc.entity.balancedetail;
import com.xtc.entity.increorder;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IncreorderService;
import com.xtc.util.Log;
import com.xtc.util.MD5Util;
import com.xtc.util.WeixinUtil;

@Controller
@RequestMapping("pay")
public class PayAddedservicesController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IncreorderService icService;
	@Autowired
	private IbalancedetailService balService;
	/**
	 * ΢��
	 * @return
	 */
	@RequestMapping(value="weixinprice",method=RequestMethod.POST)
	public @ResponseBody String wxNotice(){
	//���ܴ���������Ϣ ��ת���ɼ�ֵ�Եļ���
	Map<String, String> map;
	map = WeixinUtil.xmlToMap(request);
	String weixinSign=map.get("sign");
	String mySign = createSign(map);
	WeixinResult outRes=null;
		//��֤ǩ��
		if(weixinSign.equals(mySign)){//��֤�ɹ�
			////////////////////////////////////������Ҫ�Ĳ���///////////////////////////////////////////
			//ҵ����	result_code	��	String(16)	SUCCESS	SUCCESS/FAIL
			//�������	err_code	��	String(32)	SYSTEMERROR	���󷵻ص���Ϣ����
			//�ܽ��	total_fee	��	Int	100	�����ܽ���λΪ��
			//�ֽ�֧�����	cash_fee	��	Int	100	�ֽ�֧�������ֽ�֧�������֧�����
			//΢��֧��������	transaction_id	��	String(32)	1217752501201407033233368018	΢��֧��������
			//�̻�������	out_trade_no	��	String(32)	1212321211201407033568112322	�̻�ϵͳ�Ķ����ţ�������һ�¡�
			//�̼����ݰ�	attach	��	String(128)	123456	�̼����ݰ���ԭ������
			//֧�����ʱ��	time_end	��	String(14)	20141030133525	֧�����ʱ�䣬��ʽΪyyyyMMddHHmmss����2009��12��25��9��10��10���ʾΪ20091225091010���������ʱ�����
			if(map.get("result_code").equals("SUCCESS")){
				//////////////////////////////////����ҵ��/////////////////////////////////////
				String out_trade_no=map.get("out_trade_no");//�̻�������
				String total_fee=map.get("total_fee");//�������
				double total_feel=Double.valueOf(total_fee);
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no+total_fee);
				//////////////////////////////////////////////////////
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pay_time = dateFormat.format(now);//֧��ʱ��
				List<increorder> incre=icService.selectUserid(out_trade_no);
				String userid=incre.get(0).getUserid();
				//double inordermoney=incre.get(0).getInordermoney();
				////////////////////ҵ����/////////////////////////
				String instatus="2";
				boolean resut=icService.updateIncreorder(instatus,pay_time,total_feel,out_trade_no);
				System.out.println(total_fee+"֧�����");
				if(resut){
					balancedetail balanc=new balancedetail();
					System.out.println(userid);
					balanc.setUserid(userid);
					balanc.setStarttime(pay_time);
					balanc.setBalancetype("1");
					balanc.setMoney(total_feel);
					if(balanc!=null){
						boolean balist = balService.insert(balanc);
						System.out.println(balist);
					}
				}
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no);
				outRes=new WeixinResult("SUCCESS","ǩ����֤�ɹ���");
			}
			else{
				Log.getInstance().debug("΢��֧��֪ͨ��������룺"+map.get("err_code")+"�������������"+map.get("err_code_des"));
				outRes=new WeixinResult("SUCCESS","ҵ������֤Ϊʧ�ܣ�");
			}
		}
		else{
			//��֤ǩ��ʧ�ܣ�
			Log.getInstance().error("ǩ����֤ʧ�ܣ�");
			outRes=new WeixinResult("FAIL","ǩ����֤ʧ�ܣ�");
		}
		System.out.println(WeixinUtil.toXml(outRes));
		//��Ӧ΢���Ƿ���ܳɹ�
		return WeixinUtil.toXml(outRes);
	}
	
	@SuppressWarnings("rawtypes")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//���в��봫�εĲ�������accsii�������� 		
		Iterator it = es.iterator(); 	
		while(it.hasNext()) { 		
			Map.Entry entry = (Map.Entry)it.next(); 	
			String k = (String)entry.getKey(); 		
			Object v = entry.getValue(); 		
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) { 
				sb.append(k + "=" + v + "&"); 	
				} 	
		} 		
		sb.append("key=" + WeixinConfig.APP_KEY); 	
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase(); 
		return sign; 	
	}
	/**
	 * ֧����
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="alipayprice",method=RequestMethod.POST)
	public @ResponseBody String getByalipayprice(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
				: valueStr + values[i] + ",";
			}
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
		//�̻�������
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//֧�������׺�String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//����״̬
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//��ȡ֧������֪ͨ���ز���
		if(AlipayNotify.verify(params)){
			//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������
			if(trade_status.equals("TRADE_FINISHED")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				//�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע��
				System.out.println("�����ţ�"+out_trade_no+"���"+total_fee);
				double total_feel=Double.valueOf(total_fee);
				//////////////////////////////////////////////////////
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pay_time = dateFormat.format(now);//֧��ʱ��
				////////////////////ҵ����/////////////////////////
				System.out.println(total_fee+"֧�����");
				if(total_fee!=null || out_trade_no!=null){
					String instatus="1";
					boolean resut=icService.updateIncreorder(instatus,pay_time,total_feel,out_trade_no);
					System.out.println("resut:״̬��"+resut);
					if(resut){
						balancedetail balanc=new balancedetail();
						List<increorder> incre=icService.selectUserid(out_trade_no);
						String userid=incre.get(0).getUserid();
						balanc.setUserid(userid);
						balanc.setStarttime(pay_time);
						balanc.setBalancetype("0");
						balanc.setMoney(total_feel);
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							System.out.println(balist);
						}
					}
				}
				//////////////////////////////////////////////////////////
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				System.out.println(out_trade_no+"����ϵͳ�в鵽�ñʶ�������ϸ");
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}
			return "success";//�벻Ҫ�޸Ļ�ɾ��
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			return "fail";
		}
	}
}
