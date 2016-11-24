package com.xtc.service;

import java.util.List;

import com.xtc.entity.Tag;

public interface ITagService {
	public List<Tag> taglist(String userid);

	public boolean delete(String userid);

	// �������ֺ�userid�������
	public Tag getTag(String userid, String name);

	// ���
	public boolean create(Tag tag);

	// ����
	public boolean update(Tag t);
	public List<Tag> taglist(String userid, String type);
}
