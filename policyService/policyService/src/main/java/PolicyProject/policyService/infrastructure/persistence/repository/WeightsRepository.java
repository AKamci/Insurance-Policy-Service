package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import org.springframework.data.repository.CrudRepository;

public interface WeightsRepository extends CrudRepository<Weights, Long> {
}
