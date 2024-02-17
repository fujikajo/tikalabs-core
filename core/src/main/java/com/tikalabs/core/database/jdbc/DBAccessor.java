package com.tikalabs.core.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.tikalabs.core.database.dao.mapper.ResultSetExtractor;
import com.tikalabs.core.database.dao.mapper.RowMapper;
import com.tikalabs.core.database.dao.mapper.RowMapperResultSetExtractor;
import com.tikalabs.core.database.dao.mapper.SingleColumnRowMapper;
import com.tikalabs.core.database.utils.DBUtils;


public class DBAccessor implements DBOperations {
	
	//protected final Logger logger = LogFactory.getLog(getClass());
	
	private DataSource dataSource = null;
	private Connection con        = null; 
	private String     message    = null;
	
	//private final int nullType;
	
	public DBAccessor() {
		this(DataSourceFactory.createDataSource());
	}
	
	public DBAccessor(DataSource datasource) {
		this.setDataSource(datasource);
		this.setConnection(this.getDataSource());
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ResultSet query(String sql)  {
		
		Statement stmt = null;
		ResultSet rs   = null;
		
		stmt = this.createStatement();
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	public Connection getConnection() {
		if (con==null) {
			this.setConnection(this.getDataSource());
		}
		
		return con;
	}
	
	public String getURL() {
		
		if (con!=null) {
		  try {
		  	return con.getMetaData().getURL();
			
	    	  } catch (SQLException e) {
			
			e.printStackTrace();
		       }
		}
		return "Disconnected.";
		
		
			
	}
	
    public String getDatabaseProductName() {
		
		if (con!=null) {
		  try {
		  	return con.getMetaData().getDriverName() + " " + con.getMetaData().getDriverVersion();
			
	    	  } catch (SQLException e) {
			
			e.printStackTrace();
		       }
		}
		return "Disconnected.";
		
		
			
	}

	public void setConnection(DataSource con) {
		try {
			this.con = con.getConnection();
		} catch (SQLException e) {
			this.setMessage(e.getMessage());
			
		}
	}
	
	public boolean hasConnection() {
		return (this.con != null);
	}
	
	
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper)  {
 		return query(sql, new RowMapperResultSetExtractor<T>(rowMapper));
 	}

	public <T> T query(final String sql, final ResultSetExtractor<T> rse) {
		
		ResultSet rs = null;
		Statement stmt = this.createStatement();
		try {
			rs = stmt.executeQuery(sql);
			return rse.extractData(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			DBUtils.closeResultSet(rs);
			
		}
		return null;
		
		
	};
	
	
	public int queryForInt(String sql)  {

 		Number number = queryForObject(sql, Integer.class);
 		return (number != null ? number.intValue() : 0);

 	}
	
	public String queryForString(String sql) {
		String string = queryForObject(sql, String.class);
		return string; 
	}

	
	public <T> T queryForObject(String sql, Class<T> requiredType)  {
 		return queryForObject(sql, getSingleColumnRowMapper(requiredType));

 	}


 	public <T> T queryForObject(String sql, RowMapper<T> rowMapper)  {

 		List<T> results = query(sql, rowMapper);
 		return requiredSingleResult(results);

 	}

	
	
	public Statement createStatement() {
		try {
			return this.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	private PreparedStatement createPreparedStatement(String sql, Object[] params)  {

		PreparedStatement statement;
		try {
			statement = this.getConnection().prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				if (params[i] == null) {
					statement.setNull(i + 1, 0);
				} else if (params[i] instanceof Integer) {
					statement.setInt(i + 1, (Integer) params[i]);
				} else if (params[i] instanceof Boolean) {
					statement.setBoolean(i + 1, (Boolean) params[i]);
				} else if (params[i] instanceof Date) {
					statement.setDate(i + 1 ,  (java.sql.Date) params[i]);
				} else {
					statement.setString(i + 1, (String) params[i]);
				}
			}

			return statement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	

	}

	
	public void update(String sql, Object... params)  {
        PreparedStatement statement = null;
        statement = createPreparedStatement(sql, params);
	            try {
						statement.executeUpdate();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}  finally {
		            DBUtils.closeStatement(statement);
		        }
    }
	
	
	public void insert(String sql, Object... params) throws Exception  {
        PreparedStatement statement = null;
        boolean isWritten = false; 
        statement = createPreparedStatement(sql, params);
	            try {
						statement.executeQuery();
					} catch (Exception e) {
						
						throw e;
						//e.printStackTrace();
						
					}  finally {
		            DBUtils.closeStatement(statement);
		        }
        }


	


	protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
		return new SingleColumnRowMapper<T>(requiredType);

	}



	public <T> T requiredSingleResult(Collection<T> results)  {

		int size = (results != null ? results.size() : 0);
		if (size == 0) {

		}

		if (results.size() > 1) {

		}

		return results.iterator().next();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	

}
