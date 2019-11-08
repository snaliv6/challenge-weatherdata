package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import de.exxcellent.challenge.football.FootballTeamStandings;
import de.exxcellent.challenge.football.LeastGoalSpread;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.weather.LeastVaryingTemperature;
import de.exxcellent.challenge.weather.WeatherReport;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software design. Read: create your own
 * classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

	/**
	 * This is the main entry method of your program.
	 * 
	 * @param args The CLI arguments passed
	 */
	public static void main(String... args) {
		if (args.length != 2) {
			printUsage();
			System.exit(1);
		}

		File file = new File(args[1]);
		if (args[0].contains("football")) {
			doFootballChallenge(file);
		} else if (args[0].contains("weather")) {
			doWeatherChallenge(file);
		} else {
			printUsage();
			System.exit(1);
		}
	}

	// Print usage
	private static void printUsage() {
		System.out.println("Usage: App --football <path to input file>");
		System.out.println("Usage: App --weather <path to input file>");
	}

	// Run the football challenge.
	private static void doFootballChallenge(File input) {
		List<FootballTeamStandings> standings = null;
		try (CsvReader reader = new CsvReader(input)) {
			LeastGoalSpread analyst = new LeastGoalSpread();
			standings = analyst.findAll(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.printf("Team(s) with smallest goal spread       : %s%n", Arrays.toString(standings.toArray()));
	}

	// Run the weather challenge.
	private static void doWeatherChallenge(File input) {
		List<WeatherReport> reports = null;
		try (CsvReader reader = new CsvReader(input)) {
			LeastVaryingTemperature analyst = new LeastVaryingTemperature();
			reports = analyst.findAll(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.printf("Day(s) with smallest temperature spread : %s%n", Arrays.deepToString(reports.toArray()));
	}
}
