package com.tikalabs.core.io.csv.parser;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

public class CustomCSVParser extends AbstractCSVParser<Map<String, String>> {

	public CustomCSVParser(String filePath) {
		// ein Kommentar
		super(filePath);
	}

	@Override
	protected Map<String, String> parseRecord(CSVRecord csvRecord) {
		Map<String, String> columnDataMap = new HashMap<>();
		for (String header : getHeaderNames()) {
			String value = csvRecord.get(header);
			columnDataMap.put(header, value);
		}
		return columnDataMap;
	}

}
