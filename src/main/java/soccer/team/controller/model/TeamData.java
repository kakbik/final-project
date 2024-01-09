package soccer.team.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.team.entity.Coach;
import soccer.team.entity.League;
import soccer.team.entity.Player;
import soccer.team.entity.Team;

@Data
@NoArgsConstructor
public class TeamData {

	private Long teamId;
	private String teamName;
	private Long teamNumOfPlayers;
	private Long gamesPlayed;
	private Long teamNumOfWins;
	private Long teamNumOfLosses;
	private Long teamNumOfDraws;
	private Long teamNumOfPoints;
	private Set<TeamLeague> leagues = new HashSet<>();
	private Set<TeamPlayer> players = new HashSet<>();
	private TeamCoach coach ;

	public TeamData(Team team) {

		teamId = team.getTeamId();
		teamName = team.getTeamName();
		teamNumOfPlayers = team.getTeamNumOfPlayers();
		gamesPlayed = team.getGamesPlayed();
		teamNumOfWins = team.getTeamNumOfWins();
		teamNumOfLosses = team.getTeamNumOfLosses();
		teamNumOfDraws = team.getTeamNumOfDraws();
		teamNumOfPoints = team.getTeamNumOfPoints();
		for (League league : team.getLeagues()) {
			leagues.add(new TeamLeague(league));
		}
		for (Player player : team.getPlayers()) {
			players.add(new TeamPlayer(player));
		}
		coach = new TeamCoach (team.getCoach());
	}
	
}
