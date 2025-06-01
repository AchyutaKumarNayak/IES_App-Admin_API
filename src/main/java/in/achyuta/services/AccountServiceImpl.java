package in.achyuta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import in.achyuta.bindings.UnlockAccForm;
import in.achyuta.bindings.UserAccForm;
import in.achyuta.entities.UserEntity;
import in.achyuta.repos.UserRepo;
import in.achyuta.utils.EmailUtils;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createUserAccount(UserAccForm form) {
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(form, userEntity);
		userEntity.setUserPwd(generatePwd());
		userEntity.setAccSts("LOCKED");
		userEntity.setActiveSw("Y");
		
		userRepo.save(userEntity);
		
		//Send Mail
		
		String subject="";
		String body="";
		
		
		return emailUtils.sendMail(form.getEmail(), subject, body);
	}

	@Override
	public List<UserAccForm> fetchAllUsers() {
		
		List<UserAccForm> accForms=new ArrayList<>();
		List<UserEntity> all = userRepo.findAll();
		for(UserEntity entity:all) {
			UserAccForm form= new UserAccForm();
			BeanUtils.copyProperties(entity, form);
			accForms.add(form);
		}
		return accForms;
	}

	@Override
	public UserAccForm getUserById(Integer userId) {
		
		Optional<UserEntity> byId = userRepo.findById(userId);
		UserAccForm form= new UserAccForm();
		if(byId.isPresent()) {
			UserEntity entity = byId.get();
			BeanUtils.copyProperties(entity, form);
		}
		return form;
	}

	@Override
	public String changeAccStatus(Integer userId, String accStatus) {
		
		int cnt = userRepo.updateAccSts(userId, accStatus);
		if(cnt>0) {
			return "Status Changed";
		}
		return "Failed to Change";
	}

	@Override
	public String unlockUserAcc(UnlockAccForm form) {
		
		UserEntity userEntity = userRepo.findByUserEmail(form.getEmail());
		
		userEntity.setUserPwd(form.getNewPwd());
		userEntity.setAccSts("UNLOCKED");
		
		userRepo.save(userEntity);
		
		return "Account Unlocked";
	}
	private String generatePwd() {
		
		StringBuilder builder= new StringBuilder();
		final String upperCase="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String lowerCase=upperCase.toLowerCase();
		final String number="1234567890";
		final String specialChar="~!@#$%^&*`.?";
		final String all=upperCase+lowerCase+number+specialChar;
		int length=8;
		Random rand= new Random();
		for(int i=0;i<length;i++) {
			int nextInt = rand.nextInt(all.length()+1);
			builder.append(all.charAt(nextInt));
		}
		return builder.toString();
	}

}
