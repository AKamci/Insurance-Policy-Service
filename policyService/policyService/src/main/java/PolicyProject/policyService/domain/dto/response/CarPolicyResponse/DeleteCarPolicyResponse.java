package PolicyProject.policyService.domain.dto.response.CarPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICarPolicyResponse;

public record DeleteCarPolicyResponse(

        Long policyId
)implements ICarPolicyResponse {
}
