package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerGateway {

    CompletableFuture<Customer> create(Customer Customer);
    CompletableFuture<Customer> get(Customer Customer);
    CompletableFuture<Customer> update(Customer Customer);
    CompletableFuture<Customer> delete(Customer Customer);
    CompletableFuture<List<Customer>> getList(Specification<Customer> specification, int page, int size);
    int getTotal();






}
