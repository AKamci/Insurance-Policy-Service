package PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarthQuakeWeightsRepository extends JpaRepository<EarthQaukeWeights, Long> {
    EarthQaukeWeights findByKey(String key);

}
