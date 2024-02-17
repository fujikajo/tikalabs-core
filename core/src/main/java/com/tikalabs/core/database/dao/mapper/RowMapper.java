package com.tikalabs.core.database.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	
	 T mapRow(ResultSet resultSet,int rowRows) throws SQLException;

}
