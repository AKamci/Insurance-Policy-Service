package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.CarPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Component

public class CarPolicySpecificationBuild {

    private Specification<CarPolicy> spec;

     public Specification<CarPolicy> CarPolicyBuild(CarPolicy carPolicy) {
         setSpec(
         CarPolicySpecification.build(
                 carPolicy.getPolicyName(),
                 carPolicy.getPolicyDescription(),
                 carPolicy.getPolicyType(),
                 carPolicy.isPolicyStatus(),
                 carPolicy.getPolicyStartDate(),
                 carPolicy.getPolicyEndDate(),
                 carPolicy.getPolicyAmount(),
                 carPolicy.getLicensePlate() != null ? carPolicy.getLicensePlate().getPlate() : null,
                 carPolicy.getCustomer() != null ? carPolicy.getCustomer().getTckn() : null));

         return spec;

     }
}
