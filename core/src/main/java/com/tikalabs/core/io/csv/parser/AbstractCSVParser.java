package com.tikalabs.core.io.csv.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public abstract class AbstractCSVParser<T> {

	private final String filePath;
	private List<String> headerNames;

	protected AbstractCSVParser(String filePath) {
		this.filePath = filePath;
	}

	public List<T> parseCSV() {

		List<T> rowDataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
			this.setHeaderNames(csvParser.getHeaderNames());

			for (CSVRecord csvRecord : csvParser) {

				rowDataList.add(this.parseRecord(csvRecord));

			}

		} catch (FileNotFoundException e) {
			handleFileNotFound(e);
		} catch (IOException e) {
			handleIOException(e);
		}
		return rowDataList;
	}

	private void handleFileNotFound(FileNotFoundException e) {
		e.printStackTrace();
		// Behandlung der Ausnahme bei Datei nicht gefunden
	}

	private void handleIOException(IOException e) {
		e.printStackTrace();
		// Behandlung der allgemeinen Ein- / Ausgabefehler
	}

	public List<String> getHeaderNames() {
		return headerNames;
	}

	private void setHeaderNames(List<String> headerNames) {
		this.headerNames = headerNames;
	}

	protected abstract T parseRecord(CSVRecord csvRecord);
}
