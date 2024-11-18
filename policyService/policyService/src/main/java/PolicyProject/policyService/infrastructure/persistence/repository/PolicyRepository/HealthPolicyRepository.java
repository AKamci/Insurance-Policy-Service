package PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HealthPolicyRepository extends JpaRepository<HealthPolicy, Long>, JpaSpecificationExecutor<HealthPolicy> {
    List<HealthPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);

}
