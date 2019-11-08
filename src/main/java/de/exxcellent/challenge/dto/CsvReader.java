/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.dto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Reader of CSV formatted data.
 * <p>
 * The object is an {@link Iterator}, so it obeys the same contract. First, for
 * test for the presence of data with {@link #hasNext()}. If the method returns
 * true, access the data with {@link #next()}.
 * <p>
 * The object keeps a reference to the fields in the line. For convenience, the
 * value can be converted into an int with {@link #intValueOf(String)}.
 * <p>
 * The first line of the full must contain the names of the fields. When the
 * object is constructed, the first line is read and
 * {@link #fieldNameToIndexMap()} is set.
 * <p>
 * The object uses resources, so it must be closed after it is used.
 */
public class CsvReader implements AutoCloseable, StringFieldsIterator {
	private String fieldSep = ",";

	private final Scanner scanner;

	private Map<String, Integer> nameToIndexMap = new HashMap<>();

	// The fields in the file that were just read.
	private String[] currentFields = new String[0];

	/**
	 * Create the object with a file. This class does not take control of the file.
	 * In other words, the user still must manage the file.
	 * <p>
	 * It is assumed that the first line of the file comprises the names of the
	 * fields. When the object is constructed, the first line of the file fills the
	 * index map.
	 *
	 * @param file to read
	 * @throws FileNotFoundException if the file isn't found.
	 */
	public CsvReader(File file) throws FileNotFoundException {
		this.scanner = new Scanner(file);
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			nameToIndexMap = fieldNameToIndexMap(line);
		}
	}

	@Override
	public void close() {
		if (scanner != null) {
			scanner.close();
		}
	}

	@Override
	public boolean hasNext() {
		return scanner.hasNextLine();
	}

	@Override
	public String[] next() {
		String line = scanner.nextLine();
		currentFields = fields(line);
		return currentFields;
	}

	@Override
	public Map<String, Integer> fieldNameToIndexMap() {
		return nameToIndexMap;
	}

	@Override
	public String[] currentFields() {
		return currentFields;
	}

	// Create a map from the first line of the file.
	private Map<String, Integer> fieldNameToIndexMap(String line) {
		String[] f = fields(line);
		return IntStream.range(0, f.length).boxed().collect(Collectors.toMap(i -> f[i], i -> i));
	}

	// Split the line based on the field separator.
	private String[] fields(String line) {
		return line.split(fieldSep);
	}
}
