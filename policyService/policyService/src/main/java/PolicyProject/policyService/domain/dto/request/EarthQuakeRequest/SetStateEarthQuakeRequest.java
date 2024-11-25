package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IEarthQuakeRequest;
import jakarta.validation.constraints.NotNull;

public record SetStateEarthQuakeRequest(
        @NotNull(message = "policyId cannot be null")
        Long policyId
)implements IEarthQuakeRequest {
}
