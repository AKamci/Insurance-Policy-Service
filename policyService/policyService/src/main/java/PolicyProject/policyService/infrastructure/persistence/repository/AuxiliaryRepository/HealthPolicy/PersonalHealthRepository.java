package PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalHealthRepository extends JpaRepository<PersonalHealth, Long> {

    PersonalHealth findTopByCustomerTcknOrderByCreatedAtDesc(String tckn);


}
