package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarPolicyRepository extends CrudRepository<CarPolicy, Long>
{
    List<CarPolicy> findByCustomerId(Long customerId);
}
