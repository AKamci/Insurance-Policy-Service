package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateEarthQuakeRequest(

        //@FutureOrPresent(message = "Policy Offer date must be in the present or future")
        @NotNull(message = "Policy Offer date cannot be null")
        LocalDate policyOfferDate,

        @NotNull(message = "houseId  cannot be blank")
        Long houseId,

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


)implements IEarthQuakeRequest {
}
