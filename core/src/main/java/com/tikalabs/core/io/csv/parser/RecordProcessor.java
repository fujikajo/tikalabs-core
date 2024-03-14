package commons.csv.recordprocessor;

public interface RecordProcessor<T> {
	void process(T record);
}
