package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

public record GetCarPolicyWPlateRequest(

        String tckn,
        String licensePlateNumber


)implements ICarPolicyRequest {
}
