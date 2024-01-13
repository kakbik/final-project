package soccer.team.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.League;

public interface LeagueDao extends JpaRepository<League, Long> {

	Optional<League> findByLeagueName(String playerFullName);
}
