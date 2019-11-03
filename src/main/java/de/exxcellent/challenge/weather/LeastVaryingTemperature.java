/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.weather;

import java.util.List;

import de.exxcellent.challenge.dto.StringFieldsIterator;
import de.exxcellent.challenge.reducers.LeastSummary;

/**
 * Creates a report of days of the month with the least variation in temperature.
 */
public class LeastVaryingTemperature {
	public List<WeatherReport> findAll(StringFieldsIterator fields) {
		LeastSummary<WeatherReport, Integer> summary = LeastSummary
				.comparing(weather -> weather.getHighestTemperatureInCelcius() - weather.getLowestTemperatureInCelcius());
		while (fields.hasNext())
		{
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
