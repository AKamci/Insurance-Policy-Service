package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import java.sql.Date;

public record GetCarPolicyBetweenDateRequest(
        Date startDate,
        Date endDate

) implements ICarPolicyRequest {
}
