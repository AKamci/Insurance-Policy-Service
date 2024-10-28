package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.NotNull;

public record DeleteCarPolicyRequest(

            @NotNull(message = "policyId cannot be null")
            Long policyId
) implements ICarPolicyRequest{
}
