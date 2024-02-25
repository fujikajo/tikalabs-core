package com.tikalabs.core.gui.components.nattable.utils;

import java.util.HashMap;
import java.util.Map;

public class ListRow {

	private Map<String, Object> rawRow;

	public ListRow() {
		this.rawRow = new HashMap<String, Object>();
	}

	public ListRow(Map<String, Object> rawRow) {
		this.rawRow = rawRow;
	}

	public Object getColumnValue(String id) {

		return rawRow.get(id);

	}

	public void put(String string, Object newValue) {
		rawRow.put(string, newValue);

	}

}
