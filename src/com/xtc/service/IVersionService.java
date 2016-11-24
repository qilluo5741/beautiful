package com.xtc.service;

import java.util.List;

import com.xtc.entity.Version;

public interface IVersionService {
	public List<Version> getAll() ;
	public boolean updateversion();
	public List<Version> getAlls();
	boolean insertVersion(Version version);
}
