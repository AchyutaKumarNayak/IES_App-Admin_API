package in.achyuta.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.achyuta.entities.EligibilityEntity;

@Repository
public interface EligibilityRepo extends JpaRepository<EligibilityEntity, Integer>{

}
