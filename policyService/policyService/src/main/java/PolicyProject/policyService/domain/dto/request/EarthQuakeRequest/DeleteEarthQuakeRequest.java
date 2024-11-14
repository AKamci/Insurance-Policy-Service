package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import jakarta.validation.constraints.NotNull;

public record DeleteEarthQuakeRequest(
        @NotNull(message = "policyId cannot be null")
        Long policyId

) implements IEarthQuakeRequest {
}
