package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AddressRepository extends CrudRepository<Address, Long> {
}
