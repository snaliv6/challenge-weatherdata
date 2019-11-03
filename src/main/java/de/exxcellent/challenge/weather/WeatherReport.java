/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.weather;

/**
 * A data structure for the weather report of a single day.
 */
public class WeatherReport {
	// The day starts with 1.
	int dayOfMonth;

	int lowestTemperatureInCelcius;

	int highestTemperatureInCelcius;

	/**
	 * @return the dayOfMonth
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * @param dayOfMonth the dayOfMonth to set
	 */
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * @return the lowestTemperatureInCelcius
	 */
	public int getLowestTemperatureInCelcius() {
		return lowestTemperatureInCelcius;
	}

	/**
	 * @param lowestTemperatureInCelcius the lowestTemperatureInCelcius to set
	 */
	public void setLowestTemperatureInCelcius(int lowestTemperatureInCelcius) {
		this.lowestTemperatureInCelcius = lowestTemperatureInCelcius;
	}

	/**
	 * @return the highestTemperatureInCelcius
	 */
	public int getHighestTemperatureInCelcius() {
		return highestTemperatureInCelcius;
	}

	/**
	 * @param highestTemperatureInCelcius the highestTemperatureInCelcius to set
	 */
	public void setHighestTemperatureInCelcius(int highestTemperatureInCelcius) {
		this.highestTemperatureInCelcius = highestTemperatureInCelcius;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayOfMonth;
		result = prime * result + highestTemperatureInCelcius;
		result = prime * result + lowestTemperatureInCelcius;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherReport other = (WeatherReport) obj;
		if (dayOfMonth != other.dayOfMonth)
			return false;
		if (highestTemperatureInCelcius != other.highestTemperatureInCelcius)
			return false;
		if (lowestTemperatureInCelcius != other.lowestTemperatureInCelcius)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeatherReport [dayOfMonth=" + dayOfMonth + ", lowestTemperatureInCelcius=" + lowestTemperatureInCelcius
				+ ", highestTemperatureInCelcius=" + highestTemperatureInCelcius + "]";
	}
}
