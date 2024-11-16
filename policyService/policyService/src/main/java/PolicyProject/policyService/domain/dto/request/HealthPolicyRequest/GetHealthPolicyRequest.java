package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import jakarta.validation.constraints.NotNull;

public record GetHealthPolicyRequest(
        @NotNull(message = "ID cannot be null")
        Long policyId
) implements IHealthPolicyRequest{
}
