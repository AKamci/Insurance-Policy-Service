package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record GetCarPolicyListRequest(

        @Min(value = 1, message = "Page size must be at least 1")
        int size,

        @Min(value = 0, message = "Page number must be zero or positive")
        int page,

        @Positive(message = "ID must be a positive number")
        Long id,

        @NotBlank(message = "Policy name cannot be blank")
        String policyName,

        @NotBlank(message = "Policy description cannot be blank")
        String policyDescription,

        @NotBlank(message = "Policy type cannot be blank")
        String policyType,

        @NotNull(message = "Policy status must be specified")
        boolean policyStatus,

        @PositiveOrZero(message = "Policy amount must be zero or positive")
        Double policyAmount,

        @Positive(message = "Customer ID must be a positive number")
        Long customerId,

        @Pattern(regexp = "^[A-Z0-9-]+$", message = "Invalid license plate format")
        String licensePlateNumber,

        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        String tckn,

        @PastOrPresent(message = "Policy start date must be in the past or present")
        LocalDate policyStartDate,

        @FutureOrPresent(message = "Policy end date must be in the present or future")
        LocalDate policyEndDate

) implements ICarPolicyRequest {
}
