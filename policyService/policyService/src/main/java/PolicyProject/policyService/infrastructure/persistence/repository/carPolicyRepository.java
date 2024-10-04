package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface carPolicyRepository extends CrudRepository<carPolicy, Long>
{

}
