package PolicyProject.policyService.domain.dto.response.LicensePlateResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public record GetPlateWithCustomerResponse(


        Long id,
        Integer coverageCode,
        String plate,
        Car car,
        Customer customer,
        Long amount


) {
}
