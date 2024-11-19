package PolicyProject.policyService.application.gateways.PolicyGateway;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public interface CarPolicyGateway {

    CarPolicy create(CarPolicy carPolicy, Customer customer, LicensePlate licensePlate);
   CarPolicy get(CarPolicy carPolicy);
   CarPolicy update(CarPolicy carPolicy);
   CarPolicy delete(CarPolicy carPolicy);
    List<CarPolicy> getList(Specification<CarPolicy> carPolicy, int page, int size);
    List<CarPolicy> getCarPoliciesByCustomer(String tckn);
    List<CarPolicy> getCarPoliciesByPlateAndTckn(String plate, String tckn);
    List<CarPolicy> getCarPoliciesBetweenDate (LocalDate startDate, LocalDate endDate);
    List<CarPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);
    CarPolicy SetStateCarPolicy(CarPolicy carPolicy, PolicyState policyState);
    int getTotal();
    List<CarPolicy> findByCustomer_Tckn(String tckn);

}
