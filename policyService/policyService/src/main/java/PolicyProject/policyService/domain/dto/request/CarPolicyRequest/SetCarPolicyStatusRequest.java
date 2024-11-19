package PolicyProject.policyService.domain.dto.request.CarPolicyRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.ICarPolicyRequest;

public record SetCarPolicyStatusRequest(

        Long policyId

) implements ICarPolicyRequest {
}
