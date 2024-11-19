package PolicyProject.policyService.domain.dto.request.WeightRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IWeightRequest;

public record DeleteWeightRequest(
        String key
) implements IWeightRequest {
}
