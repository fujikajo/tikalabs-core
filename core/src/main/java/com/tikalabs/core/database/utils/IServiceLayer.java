package com.tikalabs.core.database.utils;

public interface IServiceLayer<T> {
	
	public boolean insertRow(T row) throws Exception;


}
