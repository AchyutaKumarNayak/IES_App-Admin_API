package in.achyuta.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.achyuta.bindings.DashBoardCard;
import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.UserAccForm;
import in.achyuta.entities.EligibilityEntity;
import in.achyuta.entities.UserEntity;
import in.achyuta.repos.EligibilityRepo;
import in.achyuta.repos.PlanRepo;
import in.achyuta.repos.UserRepo;
import in.achyuta.utils.EmailUtils;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private EligibilityRepo eligRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	
	@Override
	public String login(LoginForm form) {
		
		UserEntity entity = userRepo.findByUserEmailAndUserPwd(form.getEmail(), form.getPwd());
		
		if(entity==null) {
			return "Invalid Credentials";
		}
		if("LOCKED".equalsIgnoreCase(entity.getAccSts())) {
			return "Account is Locked Please Unlock First";
		}
		if(!"Y".equalsIgnoreCase(entity.getActiveSw())) {
			return "Your Account is In-Active";
		}
		return "SUCCESS";
	}

	@Override
	public boolean recoverPwd(String email) {
		UserEntity entity = userRepo.findByUserEmail(email);
		String body="";
		String subject="";
		
		return entity==null? false : emailUtils.sendMail(email, subject, body);
		
	}

	@Override
	public DashBoardCard fetchDashBoardInfo() {
		long planCount = planRepo.count();
		List<EligibilityEntity> all = eligRepo.findAll();
		long approveCnt = all.stream().filter(e->e.getPlanStatus().equalsIgnoreCase("approved")).count();
		long denyCount = all.stream().filter(e->e.getPlanStatus().equalsIgnoreCase("denied")).count();
		
		Double sum = all.stream().mapToDouble(EligibilityEntity::getBenefitAmt).sum();
		
		DashBoardCard card= new DashBoardCard();
		card.setPlansCnt(planCount);
		card.setApprovedCnt(approveCnt);
		card.setDeniedCnt(denyCount);
		card.setBenefitAmtGiven(sum);
		
		return card;
	}

	@Override
	public UserAccForm getUserByEmail(String email) {
		
		UserEntity entity = userRepo.findByUserEmail(email);
		UserAccForm form= new UserAccForm();
		BeanUtils.copyProperties(entity, form);
		
		return form;
	}

}
