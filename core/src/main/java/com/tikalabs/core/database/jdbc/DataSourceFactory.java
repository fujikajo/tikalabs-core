package com.tikalabs.core.database.jdbc;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class DataSourceFactory {

	public enum DatabaseType {
		MARIADB, ORACLE,
		// Definiere hier weitere Datenbanktypen
	}

	public static DataSource createDataSource() {

		MariaDbDataSource dataSource = null;

		try {
			dataSource = new MariaDbDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("");
			dataSource.setServerName("localhost");
			dataSource.setPortNumber(3306);
			dataSource.setDatabaseName("portfolio");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataSource;

	}

	public static DataSource createDataSource(Properties properties) throws SQLException {

		String dbTypeString = properties.getProperty("dbType").toUpperCase();
		DatabaseType dbType = DatabaseType.valueOf(dbTypeString);

		switch (dbType) {
		case MARIADB:
			MariaDbDataSource dataSource = null;
			dataSource = new MariaDbDataSource();
			dataSource.setUser(properties.getProperty("db-user"));
			dataSource.setPassword(properties.getProperty("db-password"));
			dataSource.setServerName(properties.getProperty("db-server"));
			dataSource.setPortNumber(Integer.parseInt(properties.getProperty("db-port")));
			dataSource.setDatabaseName(properties.getProperty("db-name"));
			return dataSource;
		case ORACLE:
			PoolDataSource oracleDs = PoolDataSourceFactory.getPoolDataSource();
			oracleDs.setUser(properties.getProperty("user"));
			oracleDs.setPassword(properties.getProperty("password"));
			oracleDs.setURL("jdbc:oracle:thin:@" + properties.getProperty("db-server") + ":"
					+ properties.getProperty("db-port") + ":" + properties.getProperty("db-name"));
			return oracleDs;
		default:
			throw new IllegalArgumentException("Nicht unterstützter Datenbanktyp: " + dbType);

		}
	}

}
