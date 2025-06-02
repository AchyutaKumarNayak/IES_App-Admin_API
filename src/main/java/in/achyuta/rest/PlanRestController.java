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

import in.achyuta.bindings.PlanForm;
import in.achyuta.services.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planService;
	
	@PostMapping("/plan")
	public ResponseEntity<String> createPlans(@RequestBody PlanForm form){
		
		boolean plan = planService.createPlan(form);
		
		return plan? new ResponseEntity<>("Plan is Created",HttpStatus.CREATED): new ResponseEntity<>("Plan is not Created",HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<PlanForm>> getAllPlans(){
		
		List<PlanForm> fetchAllPlans = planService.fetchAllPlans();
		return new ResponseEntity<>(fetchAllPlans,HttpStatus.OK);
		
	}
	
	@GetMapping("/plans/{planId}")
	public ResponseEntity<PlanForm> getPlanById(@PathVariable Integer planId){
		PlanForm planById = planService.getPlanById(planId);
		return new ResponseEntity<PlanForm>(planById,HttpStatus.OK);
	}
	
	@PutMapping("/plans/{planId}/{/status}")
	public ResponseEntity<List<PlanForm>> changePlanSts(@PathVariable Integer planId,@PathVariable String status){
		
		String changePlanSts = planService.changePlanSts(planId, status);
		List<PlanForm> fetchAllPlans = planService.fetchAllPlans();
		return new ResponseEntity<List<PlanForm>>(fetchAllPlans,HttpStatus.OK);
		
	}


}
