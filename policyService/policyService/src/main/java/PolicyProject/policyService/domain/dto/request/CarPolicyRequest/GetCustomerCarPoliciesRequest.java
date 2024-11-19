package PolicyProject.policyService.domain.dto.request.CarPolicyRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.ICarPolicyRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GetCustomerCarPoliciesRequest(
        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        @NotBlank(message = "TCKN cannot be blank")
        String tckn

) implements ICarPolicyRequest {
}
