/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.dto;

import java.util.Iterator;
import java.util.Map;

/**
 * Iterator of string arrays. It is expected that the indexes of the value in
 * the array are consistent. In other words, the value at index 0 always
 * represents the same field.
 */
public interface StringFieldsIterator extends Iterator<String[]> {
	/**
	 * Map the name of a field to the index of the value in the string array.
	 *
	 * @return the map
	 */
	Map<String, Integer> fieldNameToIndexMap();

	/**
	 * Get the current fields. Use this after {@link #next()}.
	 *
	 * @return an array of the current fields or an empty array if next() has not
	 *         yet been called.
	 */
	String[] currentFields();

	/**
	 * Convenience method for getting the value of field by its name. Use this after
	 * {@link #next()} is called.
	 *
	 * @param name of the field
	 * @return the value of the field
	 */
	default String valueOf(String name) {
		int i = fieldNameToIndexMap().get(name);
		return currentFields()[i];
	}

	/**
	 * Convenience method getting the value of a field as an integer. Use this
	 * after {@link #next()}. The method does not perform validity checks. It is up
	 * to the user to call this on a field with an int format.
	 *
	 * @param name of the field
	 * @return the value of the field as an int
	 * @throws NumberFormatException if the value cannot be converted to an int
	 */
	default int intValueOf(String name) {
		return Integer.parseInt(valueOf(name));
	}
}
