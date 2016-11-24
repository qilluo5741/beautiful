package com.xtc.dao;

import java.util.List;
import com.xtc.entity.Version;
public interface IVersionDao {
	public List<Version> getAll();
	public boolean updateversion();
	public List<Version> getAlls();
	boolean insertVersion(Version version);
}
