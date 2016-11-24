package com.xtc.service;

import java.util.List;

import com.xtc.entity.invitation;

public interface IinvitationService {
	public boolean insert(invitation invitation);
	public boolean updateByUserid(String date_created,String rtartmoble);
	public List<invitation> selectBymobile(String mobile);
}
