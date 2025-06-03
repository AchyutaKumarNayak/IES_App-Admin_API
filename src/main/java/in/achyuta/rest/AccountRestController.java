package in.achyuta.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger =LoggerFactory.getLogger(AccountRestController.class);
	
	@Autowired
	private AccountService accService;
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserAccForm form){
		
		logger.debug("Account Creation Process Started....");
		boolean status = accService.createUserAccount(form);
		logger.debug("Account Creation Process Ended...");
         
		if(status) {
			logger.info("Account created successfully");
			return new ResponseEntity<String>("Account Created",HttpStatus.CREATED);
		}
		logger.info("Account is not created some isssue may be raised");
		return new ResponseEntity<>("Account is Not Created",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccForm>> fetchAllUsers(){
		logger.debug("User accounts fething Started ....");
		List<UserAccForm> fetchAllUsers = accService.fetchAllUsers();
		logger.debug("User accounts fething ended ....");
        logger.info("User Accounts fetched successfully");
		return new ResponseEntity<>(fetchAllUsers,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccForm> getUserById(@PathVariable("userId") Integer userId){
		logger.debug("User accont fething on the basis of user id started ....");
		UserAccForm userById = accService.getUserById(userId);
		logger.debug("User accont fething on the basis of user id started ....");
        logger.info("User account fetched successfully");
		return new ResponseEntity<UserAccForm>(userById,HttpStatus.OK);
	}
	
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccForm>> changeAccSts(@PathVariable("userId") Integer userId,@PathVariable("status") String status){
		logger.debug("User Account account status changing process started...");
		accService.changeAccStatus(userId, status);
		logger.debug("User Account account status changing process ended");
        logger.info("User Account account status changed to"+status);
        logger.debug("After changing status fetched All user accounts process started...");
		List<UserAccForm> fetchAllUsers = accService.fetchAllUsers();
	    logger.debug("After changing status fetched All user accounts process ended...");
	    logger.info("After changing status fetched All user accounts successfully");
		 return new ResponseEntity<>(fetchAllUsers,HttpStatus.OK);
	}

}
