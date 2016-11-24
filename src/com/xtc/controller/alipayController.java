package com.xtc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.AlipayNotify;
import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.TaskInfo;
import com.xtc.entity.User;
import com.xtc.entity.balancedetail;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.dto.OrderUserRecord;
import com.xtc.entity.dto.orderadminandstatus;
import com.xtc.quartz.CancelTheOrder;
import com.xtc.quartz.TaskTimer;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
/**
 * ֧����֧��
 * @author Administrator
 */
@Controller
@RequestMapping("alipay")
public class alipayController {
	@Autowired
	private IUserService us;
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IbalancedetailService balService;
	//�ڼ���ע���ϴ�Ӧ�õ� appKey �� masterSecret
	private static final String appKey ="02598c822dede2bb0f4bac2c";////�������466f7032ac604e02fb7bda89
	private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//���ÿ��Ӧ�ö���Ӧһ��masterSecret
	private static JPushClient jpush = null;
	private static long timeToLive =  60 * 5; 
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IOrderinfoService orderservice;
	@Autowired
	private IUserService userService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
		private String  exchange_time;	//	����ʱ��	
		private String  pay_time	;	//	֧��ʱ��
		private String  pay_type;	//֧������(֧������΢�š����п������)
		private String  service_type;	//	���ͣ�1ԤԼ�ɹ� 2δ���� 3������ɣ�
		private String  order_num;	//	������
		private String  paystatus;
	 * @throws InterruptedException 
	*/
	@SuppressWarnings({ "rawtypes", "unused" })
	//http://localhost:8080/sharepark/alipay/alipaycode.action
	@RequestMapping(value="alipaycode")
	public @ResponseBody String getByUserid(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
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
				System.out.println(out_trade_no+"���Զ������Ƿ�õ���������������������������������������������");
				String exchange_time = dateFormat.format(now);//����ʱ��
				String pay_time = dateFormat.format(now);//֧��ʱ��
				String pay_type="0";
				String service_type="3";
				String paystatus="0";
				if(out_trade_no!=null){
					boolean resut =orderservice.updateOrder(exchange_time, pay_time, pay_type, service_type, paystatus,out_trade_no);
					//������ɺ�֧����ϵͳ���͸ý���״̬֪ͨ
				}else{
					return "fail";
				}
				//ע�⣺
				//�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע��
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				try {
					TaskInfo ta=new TaskInfo();
					ta.setJobName(out_trade_no);
					ta.setOrderNo(out_trade_no);
					TaskTimer.addJob(ta,CancelTheOrder.class,300);
					System.out.println(out_trade_no+"���Զ������Ƿ�õ���������������������������������������������1");
					String exchange_time = dateFormat.format(now);//����ʱ��
					String pay_time = dateFormat.format(now);//֧��ʱ��
					String pay_type="0";
					String service_type="3";
					String paystatus="1";
					if(out_trade_no!=null){
					boolean resut =orderservice.updateOrder(exchange_time, pay_time, pay_type, service_type, paystatus,out_trade_no);
					//������ɺ�֧����ϵͳ���͸ý���״̬֪ͨ
					if(resut){
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=dateFormat.format(now);
						Orderinfo orderw =orderservice.selectOneByid(out_trade_no);
						String userid=orderw.getUserId();
						double money=orderw.getMoney();
						double thankcharge=orderw.getThankcharge();
						double sharemoney=orderw.getSharemoney();
						double sunmoney=money+thankcharge;
						double k=sunmoney-sharemoney;
						balancedetail balanc=new balancedetail();
						balanc.setUserid(userid);
						balanc.setStarttime(time);
						balanc.setBalancetype("0");
						balanc.setMoney(k);
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							System.out.println(balist);
						}
					}
					List<Object> oblists=orderservice.orderisadminandstatus(out_trade_no);
					for(int h = 0; h <oblists.size();h++){
						String uid = (String)oblists.get(h);
						List<Object> oblistss=orderservice.orderstatus(uid);
						List<orderadminandstatus> olistss = new ArrayList<orderadminandstatus>();
						for (int j = 0; j < oblistss.size(); j++) {
							Object [] o  = (Object[]) oblistss.get(j);
							orderadminandstatus or = new orderadminandstatus();
							or.setIs_admin((String) o[0]);
							or.setStatus((String)o[1]);
							olistss.add(or);
						}
						if(olistss.get(0).getIs_admin().equals("2") ||olistss.get(0).getIs_admin().equals("1")||olistss.get(0).getIs_admin().equals("3") && olistss.get(0).getStatus().equals("Y")){
							jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
							jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
							jpush.setEnableSSL(true);
							List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
							List<Object> oblist=orderservice.orderJpush(out_trade_no);
							//���Է�����Ϣ����֪ͨ
							if(oblist!=null){
								for (int i = 0; i <oblist.size();i++){
									Object [] ot  = (Object[]) oblist.get(i);
									OrderUserRecord ort = new OrderUserRecord();
									ort.setName((String) ot[0]);
									ort.setMobile((String) ot[1]);
									ort.setPark_start_time((String) ot[2]);
									ort.setPark_end_time((String) ot[3]);
									ort.setPay_time((String) ot[4]);
									ort.setPlate_no((String) ot[5]);
									ort.setOderstate((String) ot[6]);
									ort.setOrder_num((String) ot[7]);
									olist.add(ort);
								}
								String Plate =olist.get(h).getPlate_no().toString();
								System.out.println("���ƺţ�------------"+Plate);
								mapRtn.put(RestDto.RESULT,olist);
							}
							String time = RandomStringUtils.randomNumeric(7);//�����
						    String sendNo=time;
						    List<Orderinfo> orders=os.selectuserid(out_trade_no);
						    String parkid=orders.get(0).getParkId();
							List<User> userlists = userService.getByparkid(parkid);
							String idss=userlists.get(0).getId();
							List<jpushinfo> jpushs=jservice.selectByUserid(idss);
							String regid=jpushs.get(0).getRegid();
							String Plate=olist.get(0).getPlate_no();
							String umobile=us.Selectmobile(idss);
							SmsSendClientExample.sendMessage(umobile,Plate);
							System.out.println("֧����֧�����͵��ֻ����룺"+umobile);
							String urt="����Ϊ"+Plate+"���û�ԤԼ������ͣ����!="+out_trade_no;
							String urts="����Ϊ"+Plate+"���û�ԤԼ������ͣ����!";
							jpush.sendRegIdMessage(sendNo,appKey,masterSecret,urt,regid);
							jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,urts,regid);
						}else{
							System.out.println("���ǹ������Ǳ���,������");
						}
						mapRtn.put(RestDto.RESULT,olistss);
						}
					}
				} catch (Exception e) {
					System.out.println("֧���� alipaycode");
					mapRtn.put(RestDto.RESULT,false);
				}
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}
			return "success";//�벻Ҫ�޸Ļ�ɾ��
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			return "fail";
		}
	}
	/**
	 * ���� sendNo ��Ψһ�����б�Ҫ��
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	//http://localhost:8080/JoinPark/alipay/jpushManage.action?out_trade_no=2016041817395
	@RequestMapping("jpushManage")
	public @ResponseBody Map<String,Object> getByjpush(ModelMap map,String out_trade_no){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
		List<Object> oblist=orderservice.orderJpush(out_trade_no);
		System.out.println("�㷵���ҵ�---"+out_trade_no);
		//���Է�����Ϣ����֪ͨ
		if(oblist!=null){
			for (int i = 0; i <oblist.size();i++){
				Object [] ot  = (Object[]) oblist.get(i);
				OrderUserRecord ort = new OrderUserRecord();
				ort.setName((String) ot[0]);
				ort.setMobile((String) ot[1]);
				ort.setPark_start_time((String) ot[2]);
				ort.setPark_end_time((String) ot[3]);
				ort.setPay_time((String) ot[4]);
				ort.setPlate_no((String) ot[5]);
				ort.setOderstate((String) ot[6]);
				ort.setOrder_num((String) ot[7]);
				olist.add(ort);
			}
			mapRtn.put(RestDto.RESULT,olist);
		}
		return mapRtn;
	}
}
