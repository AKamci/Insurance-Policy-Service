package PolicyProject.policyService.domain.dto.request.carPolicyRequest;


public record GetCarPolicyRequest(

    String tckn
)implements ICarPolicyRequest {
}
