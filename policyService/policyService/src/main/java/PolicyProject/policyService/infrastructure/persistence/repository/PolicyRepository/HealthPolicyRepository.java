package PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository;

import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthPolicyRepository extends JpaRepository<HealthPolicy, Long> {
}
