package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarthQuakeWeightsRepository extends JpaRepository<EarthQaukeWeights, Long> {
    EarthQaukeWeights findByKey(String key);

}
