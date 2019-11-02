/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.csv;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Reader of CSV formatted data.
 */
public class CsvReader {
	public Map<String, Integer> fieldNameToIndexMap(String line) {
		String[] f = fields(line);
		return IntStream.range(0, f.length).boxed().collect(Collectors.toMap(i -> f[i], i -> i));
	}

	public String[] fields(String line) {
		return line.split(",");
	}

}
