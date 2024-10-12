package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

import java.util.Date;
import java.util.List;

public interface CarPolicyGateway {

    CarPolicy create(CarPolicy carPolicy, double amount, Customer customer);
    CarPolicy get(CarPolicy carPolicy);
    CarPolicy update(CarPolicy carPolicy);
    CarPolicy delete(CarPolicy carPolicy);
    List<CarPolicy> getList();
    List<CarPolicy> getCarPoliciesByCustomer(String tckn);
    List<CarPolicy> getCarPoliciesByPlateAndTckn(String plate, String tckn);
    List<CarPolicy> getCarPoliciesBetweenDate (Date startDate, Date endDate);

}
