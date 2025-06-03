package in.achyuta.services;


import in.achyuta.bindings.DashBoardCard;
import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.UserAccForm;

public interface UserService {
	
	public String login(LoginForm form);
	
	public boolean recoverPwd(String email);
	
	public DashBoardCard fetchDashBoardInfo();
	
	public UserAccForm getUserByEmail(String email);
	
	


}
