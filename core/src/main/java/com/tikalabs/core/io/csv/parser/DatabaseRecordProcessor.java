package com.tikalabs.core.io.csv.parser;

import com.tikalabs.core.database.utils.IServiceLayer;

public class DatabaseRecordProcessor<T> implements RecordProcessor<T> {

	private final IServiceLayer<T> service;

    public DatabaseRecordProcessor(IServiceLayer<T> service) {
        this.service = service;
    }

    @Override
	public void process(T record) {
		// Logik zum Speichern des Datensatzes in der Datenbank
		saveToEmbeddedDB(record);
	}

	private void saveToEmbeddedDB(T record) {
        try {
            service.insertRow(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
