package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;


import PolicyProject.policyService.domain.dto.request.IRequest.IHealthPolicyRequest;
import jakarta.validation.constraints.NotNull;

public record DeleteHealthPolicyRequest(
        @NotNull(message = "policyId cannot be null")
        Long policyId
) implements IHealthPolicyRequest {
}
