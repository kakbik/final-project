package soccer.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.Team;

public interface TeamDao extends JpaRepository<Team, Long> {

}
