package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CustomerGateway {

    Customer create(Customer Customer);
    Customer get(Customer Customer);
    Customer update(Customer Customer);
    Customer delete(Customer Customer);
    List<Customer> getList(Specification<Customer> specification, int page, int size);
    int getTotal();






}
