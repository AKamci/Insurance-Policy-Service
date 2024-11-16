package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.EarthQuakeSpecification;
import PolicyProject.policyService.infrastructure.config.Specifications.HealthPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class HealthPolicySpecificationBuild {
    private Specification<HealthPolicy> spec;

    public Specification<HealthPolicy> HealthPolicyBuild(HealthPolicy healthPolicy, String tckn) {
        setSpec(
                HealthPolicySpecification.build(
                        healthPolicy.getPersonalHealth().getHeight(),
                        healthPolicy.getPersonalHealth().getWeight(),
                        healthPolicy.getPersonalHealth().getBmi(),
                        healthPolicy.getPersonalHealth().getAlcoholConsumption(),
                        healthPolicy.getPersonalHealth().getSmokeConsumption(),
                        healthPolicy.getPersonalHealth().getBloodType(),
                        healthPolicy.getPersonalHealth().getIsPregnant(),
                        healthPolicy.getPersonalHealth().getHasDisability(),
                        healthPolicy.getPersonalHealth().getHasPreviousSurgeries(),
                        tckn,
                        healthPolicy.getCoverage(),
                        healthPolicy.getState()
                        ));
        return spec;

    }
}
