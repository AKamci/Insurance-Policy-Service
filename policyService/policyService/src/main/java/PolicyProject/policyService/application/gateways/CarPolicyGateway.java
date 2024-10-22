package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarPolicyGateway {

    CompletableFuture<CarPolicy> create(CarPolicy carPolicy, double amount, Customer customer);
    CompletableFuture<CarPolicy> get(CarPolicy carPolicy);
    CompletableFuture<CarPolicy> update(CarPolicy carPolicy);
    CompletableFuture<CarPolicy> delete(CarPolicy carPolicy);
    CompletableFuture<List<CarPolicy>> getList(Specification<CarPolicy> carPolicy, int page, int size);
    CompletableFuture<List<CarPolicy>> getCarPoliciesByCustomer(String tckn);
    CompletableFuture<List<CarPolicy>> getCarPoliciesByPlateAndTckn(String plate, String tckn);
    CompletableFuture<List<CarPolicy>> getCarPoliciesBetweenDate (LocalDate startDate, LocalDate endDate);
    int getTotal();

}
