package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCarPolicyRequest(

        @FutureOrPresent(message = "Policy Offer date must be in the present or future")
        @NotNull(message = "Policy Offer date cannot be null")
        LocalDate policyOfferDate,

        @NotBlank(message = "Policy description cannot be blank")
        String policyDescription,

        @NotBlank(message = "Policy type cannot be blank")
        Integer coverage,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyStartDate,

        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        LocalDate policyEndDate,

        @NotNull(message = "TCKN cannot be null")
        String tckn,

        @NotNull(message = "policyAmount cannot be null")
        Long policyAmount,

        @NotNull(message = "licensePlateNumber cannot be null")
        String licensePlateNumber


) implements ICarPolicyRequest{
}
