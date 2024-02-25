package com.tikalabs.core.gui.components.table;

public abstract class AbstractTableDataClient {

	private ITableCalculationProvider calculationProvider;

	public ITableCalculationProvider getCalculationProvider() {
		return calculationProvider;
	}

	public void setCalculationProvider(ITableCalculationProvider calculationProvider) {
		this.calculationProvider = calculationProvider;
	}

}
