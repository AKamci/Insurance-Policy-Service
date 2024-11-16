package PolicyProject.policyService.domain.dto.response.HouseResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public record GetHouseWCustomerResponse(

        Long id,
        Integer number,
        Integer squareMeters,
        Customer customer,
        Building building,
        String tckn,
        Long Amount
) {
}
