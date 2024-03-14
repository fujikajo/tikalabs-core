package commons.csv.recordprocessor;

public class DatabaseRecordProcessor<T> implements RecordProcessor<T> {

	@Override
	public void process(T record) {
		// Logik zum Speichern des Datensatzes in der Datenbank
		saveToEmbeddedDB(record);
	}

	private void saveToEmbeddedDB(T record) {
		// Implementiere die Logik zum Speichern in der Datenbank
	}
}
