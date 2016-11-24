package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IredshareDao;
import com.xtc.entity.redshare;
import com.xtc.service.IredshareService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class RedshareServiceImpl implements IredshareService {
	@Autowired
	private IredshareDao dao;
	@Override
	public boolean insert(redshare redshare) {
		return dao.insert(redshare);
	}
	@Override
	public List<redshare> selectredshare(String sharemobile,String maxdate) {
		return dao.selectredshare(sharemobile,maxdate);
	}
	@Override
	public boolean updateOrdersharemoney(double sharemoney, String order_num) {
		return dao.updateOrdersharemoney(sharemoney, order_num);
	}
	@Override
	public List<redshare> selectshareid(String shareid) {
		return dao.selectshareid(shareid);
	}
	@Override
	public boolean updatesharedel(String shareid) {
		return dao.updatesharedel(shareid);
	}
	@Override
	public List<redshare> selectredmobilee(String mobile) {
		return dao.selectredmobilee(mobile);
	}
	@Override
	public List<Object> selectredshares(String sharemobile,String maxdate) {
		return dao.selectredshares(sharemobile,maxdate);
	}
	@Override
	public String selectbonuscount(String sharedate, String mobile, String sharemobile) {
		return dao.selectbonuscount(sharedate,mobile,sharemobile);
	}
	@Override
	public boolean deletesharemobile(String sharedate, String mobile) {
		return dao.deletesharemobile(sharedate, mobile);
	}
	@Override
	public redshare selectmoney(String mobile, String sharedate) {
		return dao.selectmoney(mobile, sharedate);
	}
	@Override
	public String selectOneByid(String order_num) {
		return dao.selectOneByid(order_num);
	}
}
