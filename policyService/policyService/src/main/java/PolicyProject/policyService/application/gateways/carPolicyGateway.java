package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

import java.util.List;

public interface carPolicyGateway {

    CarPolicy create(CarPolicy carPolicy, double amount, Customer customer);
    CarPolicy get(CarPolicy carPolicy);
    CarPolicy update(CarPolicy carPolicy);
    CarPolicy delete(CarPolicy carPolicy);
    CarPolicy getList(CarPolicy carPolicy);
    List<CarPolicy> getCarPoliciesByCustomer(Long Customer);

}
