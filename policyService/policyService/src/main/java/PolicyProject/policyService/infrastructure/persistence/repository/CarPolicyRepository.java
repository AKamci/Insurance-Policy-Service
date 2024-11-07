package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@Repository
public interface CarPolicyRepository extends JpaRepository<CarPolicy, Long>, JpaSpecificationExecutor<CarPolicy>
{
    List<CarPolicy> findByCustomerId(Long customerId);

    List<CarPolicy> findByCustomerTcknAndLicensePlatePlate(String tckn, String plate);

    List<CarPolicy> findByCustomerTckn(String tckn);

    List<CarPolicy> findByPolicyStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<CarPolicy> findByStateAndExpiryDateBefore(CarPolicyState state, LocalDate currentDate);



}
