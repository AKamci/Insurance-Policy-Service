package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.NotNull;

public record DeleteCarPolicyRequest(

            @NotNull(message = "ID cannot be null")
            Long id
) implements ICarPolicyRequest{
}
