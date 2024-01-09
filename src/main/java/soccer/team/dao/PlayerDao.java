package soccer.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.Player;

public interface PlayerDao extends JpaRepository<Player, Long> {

}
