package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import java.sql.Date;

public record deleteCarPolicyRequest(

            Long policyId
) implements ICarPolicyRequest{
}
