/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.weather;

import java.util.List;

import de.exxcellent.challenge.dto.StringFieldsIterator;
import de.exxcellent.challenge.reducers.LeastSummary;

/**
 * Creates a report of days of the month with the least variation in
 * temperature.
 */
public class LeastVaryingTemperature {

	/**
	 * Find all days that have the lowest temperature spread.
	 * <p>
	 * Fields are read from the iterator and mapped to daily {@link WeatherReport}s.
	 * The {@link LeastSummary} object compares the difference between the maximum
	 * and minimum temperatures of the day. The summary keeps weather reports for
	 * days with the smallest temperature difference.
	 *
	 * @param fields iterator of weather data fields
	 * @return the list of weather reports
	 */
	public List<WeatherReport> findAll(StringFieldsIterator fields) {
		LeastSummary<WeatherReport, Integer> summary = LeastSummary.comparing(
				weather -> weather.getHighestTemperatureInCelcius() - weather.getLowestTemperatureInCelcius());
		while (fields.hasNext()) {
			fields.next();
			WeatherReport report = new WeatherReport();
			report.setDayOfMonth(fields.intValueOf("Day"));
			report.setHighestTemperatureInCelcius(fields.intValueOf("MxT"));
			report.setLowestTemperatureInCelcius(fields.intValueOf("MnT"));
			summary.accept(report);
		}
		return summary.get();
	}
}
