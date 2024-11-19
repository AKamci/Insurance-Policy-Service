package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IHealthPolicyRequest;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateHealthPolicyRequest(

        //@FutureOrPresent(message = "Policy Offer date must be in the present or future")
        @NotNull(message = "Policy Offer date cannot be null")
        LocalDate policyOfferDate,

        @NotNull(message = "personalHealthId  cannot be blank")
        Long personalHealthId,

        @NotNull(message = "coverageCode  cannot be blank")
        Integer coverageCode,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyStartDate,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyEndDate,

        @NotNull(message = "TCKN cannot be null")
        String tckn,

        @NotNull(message = "policyAmount cannot be null")
        Long policyAmount


) implements IHealthPolicyRequest {
}
