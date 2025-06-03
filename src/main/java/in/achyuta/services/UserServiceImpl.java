package in.achyuta.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

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
		
		if(null==entity) {
			return false;
		}
		
		String subject= "Recover Password";
		String body=readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", entity);
		return emailUtils.sendMail(email, subject, body);		
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
	
	private String readEmailBody(String fileName,UserEntity user) {
		StringBuilder sb= new StringBuilder();
		try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
			lines.forEach(line->{
					line=line.replaceAll("${FNAME}", user.getUserFullName());
					line=line.replaceAll("${PWD}", user.getUserPwd());
					line=line.replaceAll("${EMAIL}", user.getUserEmail());
					sb.append(line);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
