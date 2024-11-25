package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.IRequest.IHealthPolicyRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateHealthPolicyRequest(
        @NotNull(message = "Policy ID cannot be null")
        @Positive(message = "Policy ID must be a positive number")
        Long policyId,

        @NotNull(message = "Policy Offer date cannot be null")
        LocalDate policyOfferDate,

        @NotBlank(message = "Policy description cannot be blank")
        String policyDescription,

        @NotNull(message = "Policy Status type cannot be blank")
        PolicyState state,

        @NotNull(message = "Policy type cannot be blank")
        Integer coverageCode,

        @PastOrPresent(message = "Policy start date must be in the past or present")
        @NotNull(message = "Policy start date cannot be null")
        LocalDate policyStartDate,

        @FutureOrPresent(message = "Policy end date must be in the present or future")
        @NotNull(message = "Policy end date cannot be null")
        LocalDate policyEndDate,

        @PositiveOrZero(message = "Policy amount must be zero or positive")
        @NotNull(message = "Policy amount cannot be null")
        Double policyAmount

) implements IHealthPolicyRequest {
}
