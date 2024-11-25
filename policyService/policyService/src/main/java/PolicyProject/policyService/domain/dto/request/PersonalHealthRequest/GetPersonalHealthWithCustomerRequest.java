package PolicyProject.policyService.domain.dto.request.PersonalHealthRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GetPersonalHealthWithCustomerRequest(
        @NotBlank
        String tckn,
        @NotNull
        Integer coverageCode
) {
}
