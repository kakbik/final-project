package soccer.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.team.entity.Coach;

public interface CoachDao extends JpaRepository<Coach, Long> {

}
