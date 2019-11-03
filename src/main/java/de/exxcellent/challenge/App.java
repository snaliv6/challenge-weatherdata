package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import de.exxcellent.challenge.csv.CsvReader;
import de.exxcellent.challenge.football.FootballTeamStandings;
import de.exxcellent.challenge.football.LeastGoalSpread;

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
			System.out.println("Usage: App --football <path to input file>");
			System.exit(1);
		}

		File file = new File(args[1]);
		List<FootballTeamStandings> standings = null;
		try (CsvReader reader = new CsvReader(file)) {
			LeastGoalSpread analyst = new LeastGoalSpread();
			standings = analyst.findAll(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.printf("Team(s) with smallest goal spread       : %s%n",
				Arrays.toString(standings.toArray()));
	}
}
