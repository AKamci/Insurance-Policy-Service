package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public record CreateCarPolicyRequest(

        @NotBlank(message = "Policy name cannot be blank")
        String policyName,

        @NotBlank(message = "Policy description cannot be blank")
        String policyDescription,

        @NotBlank(message = "Policy type cannot be blank")
        String policyType,

        @NotNull(message = "Policy status must be specified")
        boolean policyStatus,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyStartDate,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyEndDate,

        @NotNull(message = "Customer ID cannot be null")
        Long customerId,

        @NotNull(message = "Car information cannot be null")
        Car car

) implements ICarPolicyRequest{
}
