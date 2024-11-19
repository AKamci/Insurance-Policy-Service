package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IEarthQuakeRequest;

public record SetStateEarthQuakeRequest(
        Long policyId
)implements IEarthQuakeRequest {
}
