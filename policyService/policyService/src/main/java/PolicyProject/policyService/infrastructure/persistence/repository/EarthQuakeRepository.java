package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthquakePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface EarthQuakeRepository extends JpaRepository<EarthquakePolicy, Long>, JpaSpecificationExecutor<EarthquakePolicy> {
    List<EarthquakePolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);

}
