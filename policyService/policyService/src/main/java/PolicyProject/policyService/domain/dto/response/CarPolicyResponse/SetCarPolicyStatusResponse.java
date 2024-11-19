package PolicyProject.policyService.domain.dto.response.CarPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICarPolicyResponse;

public record SetCarPolicyStatusResponse(

        Long policyId
) implements ICarPolicyResponse {
}
