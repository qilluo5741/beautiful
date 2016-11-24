package com.xtc.service;

import java.util.List;

import com.xtc.entity.Tag;

public interface ITagService {
	public List<Tag> taglist(String userid);

	public boolean delete(String userid);

	// 根据名字和userid来查对象
	public Tag getTag(String userid, String name);

	// 添加
	public boolean create(Tag tag);

	// 更新
	public boolean update(Tag t);
	public List<Tag> taglist(String userid, String type);
}
