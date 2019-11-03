/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.football;

/**
 * The current standing of a particular team
 */
public class FootballTeamStandings {

	// Name of the team
	private String team;

	// The number of goals the team has scored
	private int goals;

	// The number of goals that other teams scored against this team
	private int goalsAllowed;

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the goals
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * @return the goalsAllowed
	 */
	public int getGoalsAllowed() {
		return goalsAllowed;
	}

	/**
	 * @param goalsAllowed the goalsAllowed to set
	 */
	public void setGoalsAllowed(int goalsAllowed) {
		this.goalsAllowed = goalsAllowed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goals;
		result = prime * result + goalsAllowed;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		FootballTeamStandings other = (FootballTeamStandings) obj;
		if (goals != other.goals)
			return false;
		if (goalsAllowed != other.goalsAllowed)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FootballTeamStandings [team=" + team + ", goals=" + goals + ", goalsAllowed=" + goalsAllowed + "]";
	}
}
