package com.tikalabs.core.io.csv.parser;

public interface RecordProcessor<T> {
	void process(T record);
}
