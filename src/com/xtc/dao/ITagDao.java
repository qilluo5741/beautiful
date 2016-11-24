package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Tag;

public interface ITagDao {
	public List<Tag> taglist(String userid);
	//删除
	public boolean delete(String userid);
	//根据名字和userid来查对象
	public  Tag getTag(String userid,String name);
	//添加
	public boolean create(Tag tag);
	//更新
	public boolean update(Tag t) ;
	//根据类型和userid查询集合
	public List<Tag> taglist(String userid,String type);
}
