package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CarPolicyRepository extends CrudRepository<CarPolicy, Long>
{
    List<CarPolicy> findByCustomerId(Long customerId);

    List<CarPolicy> findByCustomerTcknAndLicensePlatePlate(String tckn, String plate);

    List<CarPolicy> findByCustomerTckn(String tckn);

    List<CarPolicy> findByPolicyDateBetween(Date startDate, Date endDate);

}
