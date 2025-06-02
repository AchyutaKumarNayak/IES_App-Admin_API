package in.achyuta.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardCard {
	
	private Long plansCnt;
	private Long approvedCnt;
	private Long deniedCnt;
	private Double benefitAmtGiven;
	
	private UserAccForm user;

}
