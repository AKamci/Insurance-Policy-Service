package PolicyProject.policyService.domain.dto.response.WeightResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.IWeightResponse;

public record CreateWeightResponse(
        String key
) implements IWeightResponse {
}
