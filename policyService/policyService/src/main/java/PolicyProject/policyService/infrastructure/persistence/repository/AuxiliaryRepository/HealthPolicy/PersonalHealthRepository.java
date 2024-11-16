package PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalHealthRepository extends JpaRepository<PersonalHealth, Long> {
}
