package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public record HouseModel(

        Long id,
        Integer number,
        Integer squareMeters,
        Customer customer,
        Building building,
        String tckn,
        Long Amount
) {


}
