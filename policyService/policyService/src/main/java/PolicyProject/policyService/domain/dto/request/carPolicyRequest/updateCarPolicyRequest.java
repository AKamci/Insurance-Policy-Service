package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import java.sql.Date;

public record updateCarPolicyRequest(

        Long policyId,
        String policyName,
        String policyDescription,
        String policyType,
        Date policyDate,
        Double policyAmount
) implements ICarPolicyRequest{
}
