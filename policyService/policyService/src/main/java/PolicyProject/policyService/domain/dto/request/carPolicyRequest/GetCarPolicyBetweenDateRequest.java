package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record GetCarPolicyBetweenDateRequest(
        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        Date startDate,
        @FutureOrPresent(message = "Policy date must be in the present or future")
        @NotNull(message = "Policy date cannot be null")
        Date endDate

) implements ICarPolicyRequest {
}
