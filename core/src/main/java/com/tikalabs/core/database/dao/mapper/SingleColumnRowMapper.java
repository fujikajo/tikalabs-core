package com.tikalabs.core.database.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.tikalabs.core.database.utils.DBUtils;


public class SingleColumnRowMapper<T> implements RowMapper<T> {

	private Class<T> requiredType;

	public SingleColumnRowMapper() {
		
	}
	
	public SingleColumnRowMapper(Class<T> requiredType) {
		this.requiredType= requiredType;
	}
	
	@Override
	public T mapRow(ResultSet resultSet, int rowRows) throws SQLException {
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int nrOfColumns = rsmd.getColumnCount();
		 
		Object result = getColumnValue(resultSet, 1, this.requiredType);
		
		return (T) result;
	}
	
	protected Object getColumnValue(ResultSet rs, int index, Class<T> requiredType) throws SQLException {
		
		if (requiredType != null) {
				return DBUtils.getResultSetValue(rs, index, requiredType);
		}

		return null;
		
	}

}
