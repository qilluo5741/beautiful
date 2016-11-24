package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.bonusshare;
import com.xtc.entity.redshare;
import com.xtc.service.BonusshareService;
import com.xtc.service.IredshareService;
import com.xtc.util.RestDto;
/**
 * 红包
 * @author Administrator
 */
@Controller
@RequestMapping("share")
public class RedshareControllers{
	@Autowired
	private IredshareService service;
	@Autowired
	private BonusshareService boservice;
	@Autowired
	private HttpSession session;
	//红包分享
	@RequestMapping("insertbonus")
	public @ResponseBody Map<String, Object> insertbonus(ModelMap map,String bonusmobile,String order_num){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(bonusmobile==null && order_num!=null){
				mapRtn.put(RestDto.RESULT,false);
			}else{
				Date date=new Date();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String bonusdate = dateFormat.format(date);
				bonusshare b=new bonusshare();
				b.setBonusmobile(bonusmobile);
				b.setBonuscount(12);
				b.setBonusdate(bonusdate);
				b.setOrder_num(order_num);
				boolean i=boservice.insert(b);
				if(i){
					mapRtn.put(RestDto.RESULT,i);
				}
			}
		} catch (Exception e) {
			System.out.println("红包分享");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	@RequestMapping(value="insertshare",method=RequestMethod.GET)
	public String insertshare(ModelMap map,String sharemobile,String mobile,String order_num){
	try{
		bonusshare bonus=boservice.selectByMobile(mobile,order_num);
		String bonusdate=(String)bonus.getBonusdate().subSequence(0,19);
		String shareid=service.selectbonuscount(bonusdate,mobile,sharemobile);
		if(shareid==null){
			int bonuscount=boservice.selectbonuscount(mobile,bonusdate);
			if(bonuscount>=0){
				int boncount=bonuscount-1;
				boolean u=boservice.updateCount(boncount,mobile,bonusdate);
				if(u){
					Date date=new Date();
					SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String sharecreate = dateFormat.format(date);
					double cony=sendshare();
					redshare rshare=new redshare();
					rshare.setSharemobile(sharemobile);//领取人手机号码
					rshare.setSharemoney(cony);
					rshare.setSharecreate(sharecreate);
					rshare.setShareend(shareend());
					rshare.setMobile(mobile);
					rshare.setSharedel(0);
					rshare.setSharedate(bonusdate);
					boolean share=service.insert(rshare);
					this.session.setAttribute("share", Boolean.valueOf(share));
		            this.session.setAttribute("cony", Double.valueOf(cony));
		            this.session.setAttribute("sharemobile", sharemobile);
		            System.out.println(sharemobile);
		            this.session.setAttribute("sharecreate", sharecreate);
		            this.session.setAttribute("shareend", shareend());
					return "receive";
				}
			}else{
				System.out.println("红包已经领取完了");
				return "finished";
			}
		}else{
			System.out.println("已经领取了");
			redshare redshare=service.selectmoney(sharemobile,bonusdate);
			double cony=redshare.getSharemoney();
			String sharecreate =redshare.getSharecreate();
			String shareend=redshare.getShareend();
			session.setAttribute("sharemobile",sharemobile);
			session.setAttribute("cony",cony);
			session.setAttribute("sharecreate",sharecreate);
			session.setAttribute("shareend",shareend);
			return "receive";
		}
	} catch (Exception e) {
		System.out.println("该链接已经失效！");
		return "finished";
	}
	return "error";
}
	@RequestMapping(value="share",method=RequestMethod.GET)
	public String selectOne(ModelMap map,String mobile,String order_num){
		session.setAttribute("mobile",mobile);
		session.setAttribute("order_num",order_num);
		return "already";
	}
	public static double sendshare(){
		Random r = new Random(); 
		double d2 = r.nextDouble() * 1.9;
		String wqe=(d2+1)+"";
		double d4=Double.valueOf(wqe.substring(0,wqe.indexOf(".")+2));
		return d4;
	}
	public static String shareend(){
		long currentTime = System.currentTimeMillis() ;
		currentTime +=4320*60*1000;
		Date date=new Date(currentTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}
}