package in.achyuta.rest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.achyuta.bindings.DashBoardCard;
import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.UserAccForm;
import in.achyuta.services.UserService;

public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form) {
		String login = userService.login(form);
		return login.equalsIgnoreCase("success")?"redirect:/dashboard?email="+form.getEmail():"status";
	}
	
	public ResponseEntity<DashBoardCard> buildDashboardInfo(@PathParam("email") String email){
		DashBoardCard dashboard = userService.fetchDashBoardInfo();
		  UserAccForm user = userService.getUserByEmail(email);
		  dashboard.setUser(user);
		return new ResponseEntity<DashBoardCard>(dashboard,HttpStatus.OK);
	}

}
