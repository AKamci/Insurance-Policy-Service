package PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface CarPolicyRepository extends JpaRepository<CarPolicy, Long>, JpaSpecificationExecutor<CarPolicy>
{
    List<CarPolicy> findByCustomer_Tckn(String tckn);

    List<CarPolicy> findByCustomerTcknAndLicensePlatePlate(String tckn, String plate);

    List<CarPolicy> findByPolicyStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<CarPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);

    List<CarPolicy> findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(
            Customer customer,
            LocalDate policyEndDate,
            LocalDate policyStartDate
    );

}
