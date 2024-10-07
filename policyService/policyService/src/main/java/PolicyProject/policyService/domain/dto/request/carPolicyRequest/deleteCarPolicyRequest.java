package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import java.sql.Date;

public record deleteCarPolicyRequest(

            Long id
) implements ICarPolicyRequest{
}
