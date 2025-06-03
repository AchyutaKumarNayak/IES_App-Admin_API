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

import in.achyuta.bindings.PlanForm;
import in.achyuta.services.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planService;
	
	private Logger logger=LoggerFactory.getLogger(PlanRestController.class);
	
	@PostMapping("/plan")
	public ResponseEntity<String> createPlans(@RequestBody PlanForm form){
		logger.debug("Plan creation process started...");
		boolean plan = planService.createPlan(form);
		logger.debug("Plan creation process ended...");
         if(plan) {
        	 logger.info("Plan created successfully... ");
        	 return new ResponseEntity<>("Plan is Created",HttpStatus.CREATED);
         }
         logger.info("Plan is not created ... ");
    	 return new ResponseEntity<>("Plan is not Created",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<PlanForm>> getAllPlans(){
		logger.debug("Fetching All plans started...");
		List<PlanForm> fetchAllPlans = planService.fetchAllPlans();
		logger.debug("Fetching All plans ended...");
        logger.info("Fetching All plans completed successfully");
		return new ResponseEntity<>(fetchAllPlans,HttpStatus.OK);
		
	}
	
	@GetMapping("/plans/{planId}")
	public ResponseEntity<PlanForm> getPlanById(@PathVariable Integer planId){
		logger.debug("Fetching plan on the basis of plan id started...");
		PlanForm planById = planService.getPlanById(planId);
		logger.debug("Fetching plan on the basis of plan id ended...");
		logger.info("Fetching plan on the basis of plan id completed successfully");
		return new ResponseEntity<PlanForm>(planById,HttpStatus.OK);
	}
	
	@PutMapping("/plans/{planId}/{/status}")
	public ResponseEntity<List<PlanForm>> changePlanSts(@PathVariable Integer planId,@PathVariable String status){
		logger.debug(" plan status changing process started...");
		String changePlanSts = planService.changePlanSts(planId, status);
		logger.debug("Plan status changing process ended...");
		logger.info("Plan status "+changePlanSts+" "+status);
		logger.debug("After changing status fetched All Plans process started...");
		List<PlanForm> fetchAllPlans = planService.fetchAllPlans();
		logger.debug("After changing status fetched All Plans process ended...");
		logger.info("After changing status fetched All Plans successfully");
		return new ResponseEntity<List<PlanForm>>(fetchAllPlans,HttpStatus.OK);
		
	}


}
