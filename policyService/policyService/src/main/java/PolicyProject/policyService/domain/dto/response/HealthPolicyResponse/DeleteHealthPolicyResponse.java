package PolicyProject.policyService.domain.dto.response.HealthPolicyResponse;

public record DeleteHealthPolicyResponse(
        Long policyId
) implements IHealthPolicyResponse {
}
