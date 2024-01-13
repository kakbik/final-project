package soccer.team.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.Player;

public interface PlayerDao extends JpaRepository<Player, Long> {
	
	Optional<Player> findByPlayerFullName(String playerFullName);

}
