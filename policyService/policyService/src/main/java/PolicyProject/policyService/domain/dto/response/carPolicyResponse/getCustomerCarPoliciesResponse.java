package PolicyProject.policyService.domain.dto.response.carPolicyResponse;

import java.sql.Date;

public record getCustomerCarPoliciesResponse(

        String policyName,
        String policyDescription,
        String policyType,
        Date policyDate,
        Double policyAmount

) {
}
