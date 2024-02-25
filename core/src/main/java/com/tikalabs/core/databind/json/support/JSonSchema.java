package com.tikalabs.core.databind.json.support;

public class JSonSchema {

	/**
	 * JSON key string for fields array
	 */
	public static final String JSON_FIELDS = "fields";

	/**
	 * Supported data types
	 */
	public enum FieldType {
		BOOLEAN, DOUBLE, INTEGER, FLOAT, LONG, SHORT, CHARACTER, STRING, DATE
	}

}
