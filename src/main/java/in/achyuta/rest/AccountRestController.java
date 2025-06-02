package in.achyuta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.achyuta.bindings.UserAccForm;
import in.achyuta.services.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accService;
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserAccForm form){
		
		boolean status = accService.createUserAccount(form);
		
		return status ? new ResponseEntity<>("Account Created",HttpStatus.CREATED) : new ResponseEntity<>("Account is Not Created",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccForm>> fetchAllUsers(){
		
		List<UserAccForm> fetchAllUsers = accService.fetchAllUsers();
		return new ResponseEntity<>(fetchAllUsers,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccForm> getUserById(@PathVariable Integer userId){
		
		UserAccForm userById = accService.getUserById(userId);
		return new ResponseEntity<UserAccForm>(userById,HttpStatus.OK);
	}
	
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccForm>> changeAccSts(@PathVariable Integer userId,@PathVariable String status){
		 
		accService.changeAccStatus(userId, status);
		 List<UserAccForm> fetchAllUsers = accService.fetchAllUsers();
		 return new ResponseEntity<>(fetchAllUsers,HttpStatus.OK);
	}

}
