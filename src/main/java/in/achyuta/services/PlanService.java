package in.achyuta.services;


import java.util.List;

import in.achyuta.bindings.PlanForm;

public interface PlanService {
	
	public boolean createPlan(PlanForm form);
	
	public List<PlanForm> fetchAllPlans();
	
	public PlanForm getPlanById(Integer planId);
	
	public String changePlanSts(Integer planId,String planStatus);


}
