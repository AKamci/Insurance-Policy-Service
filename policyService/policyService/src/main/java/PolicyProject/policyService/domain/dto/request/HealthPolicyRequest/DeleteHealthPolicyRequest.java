package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import jakarta.validation.constraints.NotNull;

public record DeleteHealthPolicyRequest(
        @NotNull(message = "policyId cannot be null")
        Long policyId
) implements IHealthPolicyRequest{
}
