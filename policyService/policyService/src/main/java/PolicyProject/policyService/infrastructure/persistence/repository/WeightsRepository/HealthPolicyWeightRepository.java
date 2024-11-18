package PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository;



import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthPolicyWeightRepository extends JpaRepository<HealthPolicyWeight, Long> {
    HealthPolicyWeight findByKey(String key);
}
