package soccer.team.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import soccer.team.controller.model.TeamCoach;
import soccer.team.controller.model.TeamData;
import soccer.team.controller.model.TeamLeague;
import soccer.team.controller.model.TeamPlayer;
import soccer.team.dao.CoachDao;
import soccer.team.dao.LeagueDao;
import soccer.team.dao.PlayerDao;
import soccer.team.dao.TeamDao;
import soccer.team.entity.Coach;
import soccer.team.entity.League;
import soccer.team.entity.Player;
import soccer.team.entity.Team;

@Service
public class SoccerTeamService {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private LeagueDao leagueDao;
	
	@Autowired
	private CoachDao coachDao;

	@Transactional(readOnly = false)
	public TeamPlayer savePlayer(Long teamId, TeamPlayer teamPlayer) {

		Team team = findTeamById(teamId);
		Long playerId = teamPlayer.getPlayerId();
		Player player = findOrCreatePlayer(teamId, playerId);

		copyPlayerFields(player, teamPlayer);

		player.setTeam(team);
		team.getPlayers().add(player);
		return new TeamPlayer(playerDao.save(player));

	}

	private Player findOrCreatePlayer(Long teamId, Long playerId) {
 		if (Objects.isNull(playerId)) {
			return new Player();
		} else {

			return findPlayerById(teamId, playerId);
		}
	}

	private Player findPlayerById(Long teamId, Long playerId) {

		Player player = playerDao.findById(playerId)
				.orElseThrow(() -> new NoSuchElementException("Player with ID=" + playerId + " was not found."));

		if (player.getTeam().getTeamId() == teamId) {
			return player;
		} else {

			throw new IllegalArgumentException("Player's team ID doesn't match the team ID");
		}

	}
	private void copyCoachFields(Coach coach, TeamCoach teamCoach) {
		coach.setCoachFullName(teamCoach.getCoachFullName());
		coach.setCoachNationality(teamCoach.getCoachNationality());
		coach.setCoachId(teamCoach.getCoachId());
	}

	private void copyPlayerFields(Player player, TeamPlayer teamPlayer) {
		player.setPlayerFullName(teamPlayer.getPlayerFullName());
		player.setPlayerNationality(teamPlayer.getPlayerNationality());
		player.setPlayerId(teamPlayer.getPlayerId());
		player.setPlayerPositionOfPlay(teamPlayer.getPlayerPositionOfPlay());
	}

	public TeamData saveTeam(TeamData teamData) {

		Team team = findOrCreateTeam(teamData.getTeamId());
		copyTeamFields(team, teamData);

		Team dbTeam = teamDao.save(team);
		return new TeamData(dbTeam);

	}

	private Team findOrCreateTeam(Long teamId) {
		Team team;

		if (Objects.isNull(teamId)) {
			team = new Team();
		} else {
			team = findTeamById(teamId);
		}
		return team;
	}

	private Team findTeamById(Long teamId) {
		return teamDao.findById(teamId)
				.orElseThrow(() -> new NoSuchElementException("soccer team with ID=" + teamId + " does not exist."));
	}

	private void copyTeamFields(Team team, TeamData teamData) {
		team.setTeamId(teamData.getTeamId());
		team.setTeamName(teamData.getTeamName());
		team.setTeamNumOfPlayers(teamData.getTeamNumOfPlayers());
		team.setGamesPlayed(teamData.getGamesPlayed());
		team.setTeamNumOfWins(teamData.getTeamNumOfWins());
		team.setTeamNumOfLosses(teamData.getTeamNumOfLosses());
		team.setTeamNumOfDraws(teamData.getTeamNumOfDraws());
		team.setTeamNumOfPoints(teamData.getTeamNumOfPoints());

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional(readOnly = false)
	public TeamLeague saveLeague(Long teamId, TeamLeague teamLeague) {

		Team team = findTeamById(teamId);
		Long leagueId = teamLeague.getLeagueId();
		League league = findOrCreateLeague(leagueId, leagueId);

		copyLeagueFields(league, teamLeague);


		league.getTeams().add(team);
		team.getLeagues().add(league);
		return new TeamLeague(leagueDao.save(league));

	}

	private League findOrCreateLeague(Long teamId, Long leagueId) {
		
		if (Objects.isNull(leagueId)) {
			return new League();
		} else {

			return findLeagueById(teamId, leagueId);
		}
	}

	private League findLeagueById(Long teamId, Long leagueId) {

		League league = leagueDao.findById(leagueId)
				.orElseThrow(() -> new NoSuchElementException("League with ID=" + leagueId + " was not found."));

		Set<Team> teams = league.getTeams();

		League league2 = new League();
		for (Team team : teams) {
			if (team.getTeamId() == leagueId) {
				league2 = league;
			} else {
				throw new IllegalArgumentException("League's team ID doesn't match the soccer team ID");
			}
		}
		return league2;
	}

	private void copyLeagueFields(League league, TeamLeague teamLeague) {
		league.setLeagueName(teamLeague.getLeagueName());
		league.setLeagueType(teamLeague.getLeagueType());
		league.setLeagueId(teamLeague.getLeagueId());
		league.setLeagueNumOfTeams(teamLeague.getLeagueNumOfTeams());
	}

	@Transactional
	public List<TeamData> retrieveAllTeams() {
		List<Team> teams = teamDao.findAll();
		List<TeamData>result = new LinkedList<>();
		for (Team team : teams) {
			TeamData td = new TeamData(team);
			td.getLeagues().clear();
			td.getPlayers().clear();
			td.getCoach().delete();
			
			result.add(td);
			
		}
		return teamDao.findAll().stream().map(TeamData::new).toList();
	}

	@Transactional
	public TeamData retrieveTeamById(Long teamId) {
		Team team = findTeamById(teamId);
		return new TeamData(team);
	}

	public void deleteTeamById(Long teamId) {
		
		Team team =findTeamById(teamId);
		teamDao.delete(team);
	}

	@Transactional(readOnly = false)
	public TeamCoach saveCoach(Long teamId, TeamCoach teamCoach) {

		Team team = findTeamById(teamId);
		Long coachId = teamCoach.getCoachId();
		Coach coach = findOrCreateCoach(teamId, coachId);

		copyCoachFields(coach, teamCoach);

		coach.setTeam(team);
		//team.getCoach().add(coach);
		team.setCoach(coach);
		return new TeamCoach(coachDao.save(coach));

	}

	private Coach findOrCreateCoach(Long teamId, Long coachId) {
 		if (Objects.isNull(coachId)) {
			return new Coach();
		} else {

			return findCoachById(teamId, coachId);
		}
	}

	private Coach findCoachById(Long teamId, Long coachId) {

		Coach coach = coachDao.findById(coachId)
				.orElseThrow(() -> new NoSuchElementException("Coach with ID=" + coachId + " was not found."));

		if (coach.getTeam().getTeamId() == teamId) {
			return coach;
		} else {

			throw new IllegalArgumentException("Coach's team ID doesn't match the team ID");
		}

	}

	
}
