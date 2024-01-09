package soccer.team.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import soccer.team.controller.model.TeamData;
import soccer.team.controller.model.TeamLeague;
import soccer.team.controller.model.TeamPlayer;
import soccer.team.service.SoccerTeamService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class SoccerTeamController {

	
	@Autowired
	private SoccerTeamService teamService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TeamData insertTeam(@RequestBody TeamData teamData) {
		log.info("Creating team {}", teamData);

		return teamService.saveTeam(teamData);
	}
	@PutMapping("/{teamId}")
	public TeamData updateTeam(@PathVariable Long teamId,
			@RequestBody TeamData teamData) {
		teamData.setTeamId(teamId);
		log.info("Updating team {}", teamData);
		return teamService.saveTeam(teamData);
	}
	
	@PostMapping("/{teamId}/player")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TeamPlayer insertPlayer (@PathVariable Long teamId, @RequestBody TeamPlayer teamPlayer) {
		
		log.info("Creating employee {} for pet store with ID={}", teamPlayer, teamId);
		
		return teamService.savePlayer(teamId, teamPlayer);
		
	}
	
	@PostMapping("/{teamId}/league")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TeamLeague insertLeague (@PathVariable Long teamId, @RequestBody TeamLeague teamLeague) {
		
		log.info("Creating employee {} for pet store with ID={}", teamLeague, teamId);
		
		return teamService.saveLeague(teamId, teamLeague);
		
	}
	@GetMapping
	public List<TeamData> retrievePetStores() {
		
		log.info("Retrieving all pet stores");
		
		return teamService.retrieveAllTeams();
	}
	@GetMapping("/{petStoreId}")
	public TeamData retrieveTeamById(@PathVariable Long teamId) {
		
		log.info("Retrieving soccer team with ID={}",teamId);
		
		return teamService.retrieveTeamById(teamId);
	}
	@DeleteMapping ("/{teamId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long teamId) {
		log.info("Deleting pet store with ID={}", teamId);
		teamService.deleteTeamById(teamId);
		return Map.of("message", "Deletion of soccer team with ID="+teamId+ " was successful");
	}
}
