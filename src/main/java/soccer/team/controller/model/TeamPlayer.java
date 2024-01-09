package soccer.team.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.team.entity.Player;


@Data
@NoArgsConstructor
public class TeamPlayer {

	
	private Long playerId;
	private String playerFullName;
	private String playerNationality;
	private String playerPositionOfPlay;
	
	public TeamPlayer(Player player) {
		playerId= player.getPlayerId();
		playerFullName = player.getPlayerFullName();
		playerNationality= player.getPlayerNationality();
		playerPositionOfPlay = player.getPlayerPositionOfPlay();
			
	}
}