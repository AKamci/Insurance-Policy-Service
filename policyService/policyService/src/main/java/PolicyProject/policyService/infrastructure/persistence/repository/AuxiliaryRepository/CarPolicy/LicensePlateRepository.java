package PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensePlateRepository extends CrudRepository<LicensePlate, Long> {
    LicensePlate findByPlate(String plate);
}
