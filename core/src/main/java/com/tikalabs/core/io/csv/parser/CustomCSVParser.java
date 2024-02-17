package com.tikalabs.core.io.csv.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CustomCSVParser<T> {

    private final String filePath;

    public CustomCSVParser(String filePath) {
        this.filePath = filePath;
    }

    public List<Map<String, String>> parseCSV() {
    	
    	List<Map<String, String>> rowDataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
            
            
            
            for (CSVRecord csvRecord : csvParser) {
            	Map<String, String> columnDataMap = new HashMap<>();
                for (String header : csvParser.getHeaderNames()) {
                    String value = csvRecord.get(header);
                    columnDataMap.put(header, value);
                }
                rowDataList.add(columnDataMap);
                
                
                // Hier können Sie die Daten aus der aktuellen Zeile verarbeiten
                // Zum Beispiel: Ausgabe der Daten
                System.out.println("Daten der aktuellen Zeile:");
                for (Map.Entry<String, String> entry : columnDataMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
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

    private T parseRecord(CSVRecord csvRecord) {
        // Logik zur Analyse des CSV-Records und Erzeugung des entsprechenden Objekts
        return null;
    }
}
