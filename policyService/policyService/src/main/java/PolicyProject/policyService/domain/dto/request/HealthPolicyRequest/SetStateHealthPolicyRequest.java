package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

public record SetStateHealthPolicyRequest(
        Long policyId
) implements IHealthPolicyRequest{
}
