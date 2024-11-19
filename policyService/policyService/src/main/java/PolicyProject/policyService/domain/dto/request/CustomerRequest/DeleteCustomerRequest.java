package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.ICustomerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DeleteCustomerRequest(
        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        @NotBlank(message = "TCKN cannot be blank")
       String tckn
)implements ICustomerRequest {
}
