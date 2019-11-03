/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.football;

import java.util.List;
import de.exxcellent.challenge.dto.StringFieldsIterator;
import de.exxcellent.challenge.reducers.LeastSummary;

/**
 * 
 */
public class LeastGoalSpread {
	public List<FootballTeamStandings> findAll(StringFieldsIterator rawStandings)
	{
		LeastSummary<FootballTeamStandings, Integer> summary = LeastSummary.comparing(standings -> Math.abs(standings.getGoals() - standings.getGoalsAllowed()));
		while (rawStandings.hasNext())
		{
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
