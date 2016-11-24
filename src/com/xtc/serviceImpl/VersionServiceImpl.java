package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IVersionDao;
import com.xtc.entity.Version;
import com.xtc.service.IVersionService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class VersionServiceImpl implements IVersionService{
	@Autowired 
	IVersionDao dao;
	@Override
	public List<Version> getAll() {
		return dao.getAll();
	}
	@Override
	public boolean updateversion() {
		return dao.updateversion();
	}
	@Override
	public List<Version> getAlls() {
		return dao.getAlls();
	}
	@Override
	public boolean insertVersion(Version version) {
		return dao.insertVersion(version);
	}
}
