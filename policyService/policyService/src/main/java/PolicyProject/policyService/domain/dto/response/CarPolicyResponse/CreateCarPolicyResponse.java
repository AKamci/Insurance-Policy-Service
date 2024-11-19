package PolicyProject.policyService.domain.dto.response.CarPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICarPolicyResponse;

public record CreateCarPolicyResponse(

        Long policyId


) implements ICarPolicyResponse {
}
