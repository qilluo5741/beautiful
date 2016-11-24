package com.xtc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import com.xtc.util.DBUtil;

@Resource
public class UserDao {
	public Long getDuration(String userid) throws SQLException {
		Connection con=DBUtil.connectDB();
		PreparedStatement smt=null;
		ResultSet rs=null;
		String sql="select sum(time_to_sec(timediff(park_end_time,park_start_time))) duration from order_info where userId=?";
		long count=0;
		try {
			smt=con.prepareStatement(sql);
			smt.setString(1,userid);
			rs=smt.executeQuery();
			if (rs.next()) {
				 count  = rs.getLong("duration");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeDB(rs,smt,con);
		}
 		return count;
	}
	
	public Long getTimes(String userid) {
		Connection con=DBUtil.connectDB();
		PreparedStatement smt=null;
		ResultSet rs=null;
		String sql="select COUNT(*) times from order_info where userId=?";
		long times=0;
		try {
			smt=con.prepareStatement(sql);
			smt.setString(1,userid);
			rs=smt.executeQuery();
			if(rs.next()){
				times=rs.getLong("times");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeDB(rs,smt,con);
		}
 		return times;
	}
}