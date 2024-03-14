package com.tikalabs.core.database.jdbc;

import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedDB extends DBAccessor {

	private static final Logger logger = LoggerFactory.getLogger(EmbeddedDB.class);

	public EmbeddedDB(String dbName) {
		Properties properties = new Properties();
		properties.setProperty("db-name", dbName);
		properties.setProperty("dbType", "H2");
		try {
			this.setDataSource(DataSourceFactory.createDataSource(properties));

		} catch (SQLException e) {

			logger.error("Embedded DB konnte nicht aufgebaut werden");
		}

	}

}
