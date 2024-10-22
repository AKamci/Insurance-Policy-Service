package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.*;

public record LicensePlateModel(

        Long id,
        String plate,
        Car car,
        Customer customer,
        Long amount
) {
}
