package PolicyProject.policyService.domain.dto.response.HealthPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.IHealthPolicyResponse;

public record SetStateHealthPolicyResponse(
        Long policyId

)implements IHealthPolicyResponse {
}
