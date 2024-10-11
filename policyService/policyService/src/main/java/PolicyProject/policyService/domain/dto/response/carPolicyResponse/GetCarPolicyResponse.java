package PolicyProject.policyService.domain.dto.response.carPolicyResponse;

import java.sql.Date;

public record GetCarPolicyResponse(

        Long id,
        String policyName,
        String policyDescription,
        String policyType,
        Date policyDate,
        Double policyAmount,
        Long customerId

) implements ICarPolicyResponse {
}
