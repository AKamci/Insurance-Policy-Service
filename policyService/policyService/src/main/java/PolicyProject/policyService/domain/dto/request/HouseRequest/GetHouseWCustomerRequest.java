package PolicyProject.policyService.domain.dto.request.HouseRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public record GetHouseWCustomerRequest(

        Integer number,
        Integer apartmentNumber,
        String city,
        String district,
        String neighborhood,
        String tckn
) {
}
