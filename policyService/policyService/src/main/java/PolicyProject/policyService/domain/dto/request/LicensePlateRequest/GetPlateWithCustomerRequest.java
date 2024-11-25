package PolicyProject.policyService.domain.dto.request.LicensePlateRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GetPlateWithCustomerRequest(

        @NotBlank
        String plate,
        @NotNull
        Integer coverageCode
) {
}
