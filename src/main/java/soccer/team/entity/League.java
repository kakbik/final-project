package soccer.team.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class League {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leagueId;
	private String leagueName;
	private String leagueType;
	private Long leagueNumOfTeams;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "leagues",cascade = CascadeType.PERSIST)
	private Set<Team> teams = new HashSet<>();

}
