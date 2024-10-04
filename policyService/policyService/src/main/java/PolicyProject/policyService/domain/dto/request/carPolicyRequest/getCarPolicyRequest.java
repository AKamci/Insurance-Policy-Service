package PolicyProject.policyService.domain.dto.request.carPolicyRequest;


import java.sql.Date;

public record getCarPolicyRequest(

    Long policyId

)implements ICarPolicyRequest {
}
