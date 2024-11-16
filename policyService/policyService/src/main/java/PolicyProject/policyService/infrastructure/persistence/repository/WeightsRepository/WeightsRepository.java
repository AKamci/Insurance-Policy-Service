package PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightsRepository extends JpaRepository<Weights, Long> {
    Weights findByKey(String key);
}
