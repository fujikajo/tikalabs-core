package com.tikalabs.core.database.jdbc;

import java.sql.ResultSet;
import java.util.List;

import com.tikalabs.core.database.dao.mapper.RowMapper;



public interface DBOperations {
	
	public ResultSet query(String sql) ;
	<T> List<T>   query(String sql,RowMapper<T> rowMapper);
	
	

}
