package PolicyProject.policyService.domain.dto.request.carPolicyRequest;


import jakarta.validation.constraints.NotNull;

public record GetCarPolicyRequest(

     @NotNull(message = "ID cannot be null")
    Long policyId
)implements ICarPolicyRequest {
}
