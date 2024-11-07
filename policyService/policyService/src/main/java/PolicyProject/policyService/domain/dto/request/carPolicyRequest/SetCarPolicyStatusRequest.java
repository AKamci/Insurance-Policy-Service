package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

public record SetCarPolicyStatusRequest(

        Long policyId

) implements ICarPolicyRequest{
}
