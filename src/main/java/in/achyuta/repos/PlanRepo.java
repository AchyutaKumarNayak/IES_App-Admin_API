package in.achyuta.repos;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.achyuta.entities.PlanEntity;

@Repository
public interface PlanRepo extends JpaRepository<PlanEntity, Integer>{
	
	@Query("update PlanEntity set activeSw=:status where planId=:planId")
	public int changePlanSts(Integer planId,String status);
=======
public interface PlanRepo {
>>>>>>> develop

}
