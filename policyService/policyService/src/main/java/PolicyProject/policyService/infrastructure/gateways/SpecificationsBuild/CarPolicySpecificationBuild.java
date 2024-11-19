package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.CarPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component

public class CarPolicySpecificationBuild {

    private Specification<CarPolicy> spec;

     public Specification<CarPolicy> CarPolicyBuild(CarPolicy carPolicy, String tckn, String plate) {
         setSpec(
         CarPolicySpecification.build(
                 carPolicy.getPolicyDescription(),
                 carPolicy.getCoverage(),
                 carPolicy.getState(),
                 carPolicy.getPolicyStartDate(),
                 carPolicy.getPolicyEndDate(),
                 carPolicy.getPolicyAmount(),
                 plate,
                 tckn));
         return spec;

     }
}
