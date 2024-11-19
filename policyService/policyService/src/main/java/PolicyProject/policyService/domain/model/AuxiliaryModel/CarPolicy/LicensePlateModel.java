package PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

import java.time.LocalDate;

public record LicensePlateModel(

        Long id,
        String plate,
        Car car,
        Customer customer,
        Integer coverageCode,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        Long amount


) {
}
