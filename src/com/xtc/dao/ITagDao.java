package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Tag;

public interface ITagDao {
	public List<Tag> taglist(String userid);
	//ɾ��
	public boolean delete(String userid);
	//�������ֺ�userid�������
	public  Tag getTag(String userid,String name);
	//���
	public boolean create(Tag tag);
	//����
	public boolean update(Tag t) ;
	//�������ͺ�userid��ѯ����
	public List<Tag> taglist(String userid,String type);
}
