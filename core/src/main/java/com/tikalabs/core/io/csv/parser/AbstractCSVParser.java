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
	private int desiredHeaderIndex;
	private final RecordProcessor<T> recordProcessor; // Verarbeitungslogik

	protected AbstractCSVParser(String filePath) {
		this.filePath = filePath;
		this.setDesiredHeaderIndex(0);
		this.recordProcessor = new ListRecordProcessor<T>();
	}

	protected AbstractCSVParser(String filePath, int headerIndex) {
		this.filePath = filePath;
		this.setDesiredHeaderIndex(headerIndex);
		this.recordProcessor = new ListRecordProcessor<T>();
	}

	protected AbstractCSVParser(String filePath, RecordProcessor<T> recordProcessor) {
		this.filePath = filePath;
		this.setDesiredHeaderIndex(0);
		this.recordProcessor = recordProcessor;
	}

	protected AbstractCSVParser(String filePath, RecordProcessor<T> recordProcessor, int headerIndex) {
		this.filePath = filePath;
		this.setDesiredHeaderIndex(headerIndex);
		this.recordProcessor = recordProcessor;
	}

	public List<T> parseCSV() {

		List<T> rowDataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			if (this.getDesiredHeaderIndex() > 0) {
				for (int i = 0; i < this.getDesiredHeaderIndex(); i++)
					br.readLine();
			}
			CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
			this.setHeaderNames(csvParser.getHeaderNames());

			for (CSVRecord csvRecord : csvParser) {

				if (recordProcessor != null) {
					recordProcessor.process(this.parseRecord(csvRecord));
				}


			}

		} catch (FileNotFoundException e) {
			handleFileNotFound(e);
		} catch (IOException e) {
			handleIOException(e);
		}
		return rowDataList;
	}

	public void setDesiredHeaderIndex(int headerIndex) {
		this.desiredHeaderIndex = headerIndex;
	}

	public int getDesiredHeaderIndex() {
		return this.desiredHeaderIndex;
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
