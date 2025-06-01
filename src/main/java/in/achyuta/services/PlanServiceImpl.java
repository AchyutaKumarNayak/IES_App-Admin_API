package in.achyuta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.achyuta.bindings.PlanForm;
import in.achyuta.entities.PlanEntity;
import in.achyuta.repos.PlanRepo;

public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanRepo planRepo;

	@Override
	public boolean createPlan(PlanForm form) {
		PlanEntity entity= new PlanEntity();
		BeanUtils.copyProperties(form, entity);
		
		PlanEntity save = planRepo.save(entity);
		if(save!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<PlanForm> fetchAllPlans() {
		
		List<PlanEntity> all = planRepo.findAll();
		List<PlanForm> planForms=new ArrayList<>();
		
		for(PlanEntity en:all) {
			PlanForm form= new PlanForm();
			BeanUtils.copyProperties(en, form);
			planForms.add(form);
		}
		return planForms;
	}

	@Override
	public PlanForm getPlanById(Integer planId) {
		
		Optional<PlanEntity> byId = planRepo.findById(planId);
		PlanForm form= new PlanForm();
		if(byId.isPresent()) {
			PlanEntity planEntity = byId.get();
			BeanUtils.copyProperties(planEntity, form);
		}
		
		return form;
	}

	@Override
	public String changePlanSts(Integer planId, String planStatus) {
		
		int cnt = planRepo.changePlanSts(planId, planStatus);
		if(cnt>0) {
			return "Status Changed";
		}
		
		return "Failed to Change";
	}

}
