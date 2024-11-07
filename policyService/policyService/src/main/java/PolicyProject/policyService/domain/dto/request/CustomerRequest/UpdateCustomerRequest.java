package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import jakarta.validation.constraints.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record UpdateCustomerRequest(
        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        @NotBlank(message = "TCKN cannot be blank")
        String tckn,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 255, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Address cannot be blank")
        @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
        String address,

        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
        @NotBlank(message = "Phone cannot be blank")
        String phone,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @Past(message = "Birth date must be in the past")
        @NotNull(message = "Birth date cannot be null")
        LocalDate birthDay,

        @NotBlank(message = "Gender cannot be blank")
        Integer gender



) implements ICustomerRequest{
}
