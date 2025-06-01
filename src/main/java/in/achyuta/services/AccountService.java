package in.achyuta.services;

<<<<<<< HEAD
import java.util.List;

import in.achyuta.bindings.UnlockAccForm;
import in.achyuta.bindings.UserAccForm;

public interface AccountService {
	
	public boolean createUserAccount(UserAccForm form);
	
	public List<UserAccForm> fetchAllUsers();
	
	public UserAccForm getUserById(Integer userId);
	
	public String changeAccStatus(Integer userId,String accStatus);
	
	public String unlockUserAcc(UnlockAccForm form);
	
	
		
	
=======
public interface AccountService {
>>>>>>> develop

}
