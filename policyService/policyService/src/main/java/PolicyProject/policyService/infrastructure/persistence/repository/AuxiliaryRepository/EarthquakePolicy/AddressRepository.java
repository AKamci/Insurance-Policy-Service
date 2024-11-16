package PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AddressRepository extends CrudRepository<Address, Long> {
}
