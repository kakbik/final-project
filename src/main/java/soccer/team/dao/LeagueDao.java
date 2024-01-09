package soccer.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.League;

public interface LeagueDao extends JpaRepository<League, Long> {

}
