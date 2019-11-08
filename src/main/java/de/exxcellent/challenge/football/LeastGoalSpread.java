/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.football;

import java.util.List;

import de.exxcellent.challenge.analysis.LeastSummary;
import de.exxcellent.challenge.io.StringFieldsIterator;

/**
 * Creates a report of teams with the least spread goal difference.
 */
public class LeastGoalSpread {
	/**
	 * Find all teams that have the smallest absolute goal difference. Team
	 * standings are provided in a {@link StringFieldsIterator}. String fields are
	 * converted into {@link FootballTeamStandings} objects and analyzed. The result
	 * of the analysis is a list of {@link FootballTeamStandings}.
	 *
	 * @param rawStandings iterator of team standings data
	 * @return the list of teams with the smallest absolute goal difference
	 */
	public List<FootballTeamStandings> findAll(StringFieldsIterator rawStandings) {
		LeastSummary<FootballTeamStandings, Integer> summary = LeastSummary
				.comparing(standings -> Math.abs(standings.getGoals() - standings.getGoalsAllowed()));

		while (rawStandings.hasNext()) {
			rawStandings.next();
			FootballTeamStandings standings = new FootballTeamStandings();
			standings.setTeam(rawStandings.valueOf("Team"));
			standings.setGoals(rawStandings.intValueOf("Goals"));
			standings.setGoalsAllowed(rawStandings.intValueOf("Goals Allowed"));
			summary.accept(standings);
		}
		return summary.get();
	}
}
