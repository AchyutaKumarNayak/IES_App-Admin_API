package in.achyuta.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnlockAccForm {

	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;
}
