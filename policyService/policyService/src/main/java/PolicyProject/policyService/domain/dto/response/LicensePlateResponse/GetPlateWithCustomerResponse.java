package PolicyProject.policyService.domain.dto.response.LicensePlateResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public record GetPlateWithCustomerResponse(


        Long id,
        String plate,
        Car car,
        Customer customer,
        Long amount


) {
}
