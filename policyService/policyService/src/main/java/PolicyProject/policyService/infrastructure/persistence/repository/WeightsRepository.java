package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WeightsRepository extends JpaRepository<Weights, Long> {
    Weights findByKey(String key);
}
