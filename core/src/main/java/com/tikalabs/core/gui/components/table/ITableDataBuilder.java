package com.tikalabs.core.gui.components.table;

import java.util.List;

public interface ITableDataBuilder<T> {

	public List<T> createTableData();

	public String[] getPropertyNames();

}
