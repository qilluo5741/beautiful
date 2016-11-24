package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IStateinfoDao;
import com.xtc.entity.Stateinfo;
import com.xtc.service.IStateinfoService;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class StateinfoServiceImpl implements IStateinfoService{
    @Autowired
	private IStateinfoDao dao;
	@Override
	public List<Stateinfo> selectAll(String parkid) {
		return dao.selectAll(parkid);
	}

	@Override
	public Stateinfo selectOneBysid(String sid) {
		return dao.selectOneBysid(sid);
	}

	@Override
	public boolean insert(Stateinfo stateinfo) {
		return dao.insert(stateinfo);
	}

	@Override
	public boolean updateStateinfo() {
		return dao.updateStateinfo();
	}

	@Override
	public boolean deleteStateinfo(String userid) {
		return dao.deleteStateinfo(userid);
	}
}
