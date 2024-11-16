package PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository;

import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliciesRepository  extends JpaRepository<Policies, Long> {

}
