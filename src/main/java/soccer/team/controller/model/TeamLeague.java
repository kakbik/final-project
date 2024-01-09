package soccer.team.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.team.entity.League;

@Data
@NoArgsConstructor
public class TeamLeague {

	private Long leagueId;
	private String leagueName;
	private String leagueType;
	private Long leagueNumOfTeams;
	
	
	public TeamLeague(League league) {
		leagueId = league.getLeagueId();
		leagueName = league.getLeagueName();
		leagueType = league.getLeagueType();
		leagueNumOfTeams = league.getLeagueNumOfTeams();
		
		
		
	}
}
