package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IHealthPolicyRequest;

public record SetStateHealthPolicyRequest(
        Long policyId
) implements IHealthPolicyRequest {
}
