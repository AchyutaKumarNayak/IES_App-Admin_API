package in.achyuta.repos;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.achyuta.entities.UserEntity;



@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer>{
	
	@Query("update UserEntity set accSts=:status where userId=:userId")
	public int updateAccSts(Integer userId,String status);
	
	public UserEntity findByUserEmail(String userEmail);
	
	public UserEntity findByUserEmailAndUserPwd(String userEmail,String userPwd);
=======
public interface UserRepo {
>>>>>>> develop

}
