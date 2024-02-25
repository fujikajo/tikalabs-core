package com.tikalabs.core.gui.components.table;

public interface ITableCalculationProvider {

	public Double getDataValue(int columnIndex, int rowIndex);

	/**
	 *
	 * @return The number of columns this {@link IDataProvider} handles.
	 */
	public int getColumnCount();

	/**
	 *
	 * @return The number of rows this {@link IDataProvider} handles.
	 */
	public int getRowCount();

}
