package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.CustomerSpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Component
public class CustomerSpecificationBuild {

    private Specification<Customer> spec;

    public Specification<Customer> CustomerBuild(Customer customer) {
        setSpec(
                CustomerSpecification.build(
                        customer.getName(),
                        customer.getTckn(),
                        customer.getAddress(),
                        customer.getPhone(),
                        customer.getEmail(),
                        customer.getBirthDay(),
                        customer.getGender()
                )
        );

        return spec;
    }
}
