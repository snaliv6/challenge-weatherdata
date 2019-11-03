package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import de.exxcellent.challenge.csv.CsvReader;
import de.exxcellent.challenge.football.FootballTeamStandings;
import de.exxcellent.challenge.reducers.LeastSummary;

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
		LeastSummary<FootballTeamStandings, Integer> summary = LeastSummary
				.comparing(standings -> Math.abs(standings.getGoals() - standings.getGoalsAllowed()));
		try (CsvReader reader = new CsvReader(file)) {
			while (reader.hasNext()) {
				reader.next();
				String team = reader.valueOf("Team");
				int goals = reader.intValueOf("Goals");
				int goalsAllowed = reader.intValueOf("Goals Allowed");
				FootballTeamStandings st = new FootballTeamStandings();
				st.setTeam(team);
				st.setGoals(goals);
				st.setGoalsAllowed(goalsAllowed);
				summary.accept(st);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.printf("Team(s) with smallest goal spread       : %s%n",
				Arrays.toString(summary.leasts().toArray()));
	}
}
