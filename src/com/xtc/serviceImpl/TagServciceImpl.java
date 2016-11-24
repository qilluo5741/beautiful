package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.ITagDao;
import com.xtc.entity.Tag;
import com.xtc.service.ITagService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TagServciceImpl implements ITagService {
	@Autowired
	private ITagDao dao;

	@Override
	public List<Tag> taglist(String userid) {
		return dao.taglist(userid);
	}

	@Override
	public boolean delete(String userid) {
		return dao.delete(userid);
	}

	@Override
	public Tag getTag(String userid, String name) {
		return dao.getTag(userid, name);
	}

	@Override
	public boolean create(Tag tag) {
		return dao.create(tag);
	}

	@Override
	public boolean update(Tag t) {
		return dao.update(t);
	}

	@Override
	public List<Tag> taglist(String userid, String type) {
		return dao.taglist(userid,type);
	}

}
