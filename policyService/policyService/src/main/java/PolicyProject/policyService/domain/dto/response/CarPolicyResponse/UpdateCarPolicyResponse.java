package PolicyProject.policyService.domain.dto.response.CarPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICarPolicyResponse;

public record UpdateCarPolicyResponse(

       Long policyId

) implements ICarPolicyResponse {
}
