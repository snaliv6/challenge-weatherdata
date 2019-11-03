/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.exxcellent.challenge.dto.StringFieldsIterator;

/**
 * Reader of CSV formatted data. The class assumes the first line of the file
 * contains the names of the fields.
 */
public class CsvReader implements AutoCloseable, StringFieldsIterator {
	private String fieldSep = ",";

	private final Scanner scanner;

	private Map<String, Integer> nameToIndexMap = new HashMap<>();

	// The fields in the file that were just read.
	private String[] currentFields = new String[0];

	/**
	 * Create the object with a file. This class does not take control of the file.
	 * In other words, the user still must manage the file (e.g. close, etc.)
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
