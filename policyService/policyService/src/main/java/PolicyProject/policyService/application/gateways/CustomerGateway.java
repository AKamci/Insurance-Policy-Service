package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerGateway {

    Customer create(Customer customer);
    Customer get(Customer customer);
    Customer update(Customer customer);
    Customer delete(Customer customer);
    List<Customer> getList(Specification<Customer> specification, int page, int size);
    int getTotal();






}
