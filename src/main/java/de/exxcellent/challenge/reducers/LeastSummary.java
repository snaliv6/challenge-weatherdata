/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.reducers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Keeps a collection of objects that share a common value. The common value is
 * the lowest value the object has seen. Invoking the {@link #accept(Object)}
 * method will keep the object if the its value is equal to the common lowest
 * value, it is kept. If the value is less than the common lowest value, the
 * previously kept objects are removed and the object is kept.
 * <p>
 * Instances of the summary are created with the factor method
 * {@link #comparing(Function)}. The user provides a function to extract the
 * value to compare from the object. The value to compare must be
 * {@link Comparable}.
 */
public interface LeastSummary<T, U extends Comparable<U>> {
	/**
	 * Factory for creating an instance of the summary. The user provides a function
	 * to extract the value of the object for the comparisons of leasts.
	 * 
	 * @param <T>            Type of object to provide summaries of
	 * @param <U>            A {@link Comparable} type to compare
	 * @param valueExtractor function to get the value to compare from the object
	 * @return the summary
	 */
	static <T, U extends Comparable<U>> LeastSummary<T, U> comparing(Function<T, U> valueExtractor) {
		return new LeastSummary<T, U>() {
			private List<T> list = new ArrayList<>();

			@Override
			public List<T> leasts() {
				return list;
			}

			@Override
			public U value(T object) {
				return valueExtractor.apply(object);
			}
		};
	}

	/**
	 * The list of object with a common lower value or an empty list, if no objects
	 * have been accepted yet.
	 *
	 * @return the list of objects
	 */
	List<T> leasts();

	/**
	 * Extract a value from an object for the comparison.
	 *
	 * @param object to extract a value from
	 * @return the value
	 */
	U value(T object);

	/**
	 * Accept an object as a candidate for having the lowest value in the
	 * collection. The object is added to the rest if its value is equal to any
	 * other in the list. If the value of the object is lower than current
	 * collection, it replaces the collection with itself. Otherwise, it is
	 * discarded.
	 *
	 * @param object
	 */
	default void accept(T object) {
		if (leasts().isEmpty()) {
			// This is first object so no comparison is needed. Just add it to the list of
			// leasts.
			leasts().add(object);
		} else if (leasts().stream().map(this::value).filter(least -> least.compareTo(value(object)) == 0).findAny()
				.isPresent()) {
			// Add the object because its value is equal to ones in the list.
			leasts().add(object);
		} else if (leasts().stream().map(this::value).filter(least -> least.compareTo(value(object)) > 0).findAny()
				.isPresent()) {
			// The objects value is less than the values in the list. Clear the list and add
			// this object as the new least.
			leasts().clear();
			leasts().add(object);
		}
	}
}
