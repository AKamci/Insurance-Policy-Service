package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

public record GetCustomerRequest(
        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        @NotBlank(message = "TCKN cannot be blank")
        String tckn
) implements ICustomerRequest{
}
