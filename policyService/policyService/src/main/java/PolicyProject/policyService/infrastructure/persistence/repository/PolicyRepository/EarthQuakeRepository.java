package PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface EarthQuakeRepository extends JpaRepository<EarthquakePolicy, Long>, JpaSpecificationExecutor<EarthquakePolicy> {
    List<EarthquakePolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);

    List<EarthquakePolicy> findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(
            Customer customer,
            LocalDate policyEndDate,
            LocalDate policyStartDate
    );

}
