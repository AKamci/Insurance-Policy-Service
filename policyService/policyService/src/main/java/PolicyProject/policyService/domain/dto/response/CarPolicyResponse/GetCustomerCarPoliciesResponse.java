package PolicyProject.policyService.domain.dto.response.CarPolicyResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICarPolicyResponse;

import java.sql.Date;

public record GetCustomerCarPoliciesResponse(

        String policyName,
        String policyDescription,
        int policyType,
        Date policyDate,
        Double policyAmount

) implements ICarPolicyResponse {
}
