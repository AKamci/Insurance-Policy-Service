package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import java.sql.Date;

public record getCustomerCarPoliciesRequest(

        Long customerId

) implements ICarPolicyRequest{
}
