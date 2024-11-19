package PolicyProject.policyService.domain.dto.request.CarPolicyRequest;


import PolicyProject.policyService.domain.dto.request.IRequest.ICarPolicyRequest;
import jakarta.validation.constraints.NotNull;

public record GetCarPolicyRequest(

     @NotNull(message = "ID cannot be null")
    Long policyId
)implements ICarPolicyRequest {
}
