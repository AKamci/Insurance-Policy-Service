package PolicyProject.policyService.domain.dto.request.CustomerRequest;


import PolicyProject.policyService.domain.dto.request.IRequest.ICustomerRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateCustomerRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        @NotBlank(message = "TCKN cannot be blank")
        String tckn,

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

        @NotNull(message = "Gender cannot be blank")
        Integer gender


) implements ICustomerRequest {
}
