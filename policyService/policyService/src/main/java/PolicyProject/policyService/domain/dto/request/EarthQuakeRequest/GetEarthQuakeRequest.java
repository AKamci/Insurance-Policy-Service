package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IEarthQuakeRequest;
import jakarta.validation.constraints.NotNull;

public record GetEarthQuakeRequest(
        @NotNull(message = "ID cannot be null")
        Long policyId

) implements IEarthQuakeRequest {
}
