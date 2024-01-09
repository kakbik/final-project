package soccer.team.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;
	private String teamName;
	private Long teamNumOfPlayers;
	private Long gamesPlayed;
	private Long teamNumOfWins;
	private Long teamNumOfLosses;
	private Long teamNumOfDraws;
	private Long teamNumOfPoints;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade= CascadeType.PERSIST)
	@JoinTable(name = "team_league",
	joinColumns = @JoinColumn(name = "team_id"),
	inverseJoinColumns=@JoinColumn(name="league_id"))
	private Set<League> leagues = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Player> players = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	private Coach coach = new Coach();
	

}
