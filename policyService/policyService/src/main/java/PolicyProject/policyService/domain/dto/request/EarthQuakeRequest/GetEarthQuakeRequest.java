package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import jakarta.validation.constraints.NotNull;

public record GetEarthQuakeRequest(
        @NotNull(message = "ID cannot be null")
        Long policyId

) implements IEarthQuakeRequest{
}
