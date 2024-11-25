package PolicyProject.policyService.domain.dto.request.HouseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GetHouseWCustomerRequest(

        @NotNull
        Integer number,
        @NotNull
        Integer apartmentNumber,
        @NotNull
        String city,
        @NotNull
        String district,
        @NotNull
        String neighborhood,
        @NotNull
        Integer coverageCode,
        @NotBlank
        String tckn
) {
}
