package soccer.team.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.team.entity.Coach;


@Data
@NoArgsConstructor
public class TeamCoach {

	
	private Long coachId;
	private String coachFullName;
	private String coachNationality;
	
	public TeamCoach(Coach coach) {
		coachId= coach.getCoachId();
		coachFullName = coach.getCoachFullName();
		coachNationality= coach.getCoachNationality();
			
	}

	public void delete() {
		this.coachFullName = null;
		this.coachNationality = null;
		
	}

}