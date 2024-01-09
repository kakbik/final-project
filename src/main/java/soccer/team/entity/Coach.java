package soccer.team.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Coach {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long coachId;
	private String coachFullName;
	private String coachNationality;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;
}
