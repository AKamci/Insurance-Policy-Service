package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

public record GetCustomerListRequest(
        @Positive(message = "ID must be a positive number")
        Long id,

        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Pattern(regexp = "\\d{11}", message = "TCKN must be 11 digits")
        String tckn,

        @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
        String address,

        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
        String phone,

        @Email(message = "Email must be valid")
        String email,

        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDay,

        @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
        String gender,

        @Min(value = 0, message = "Page number cannot be negative")
        int page,

        @Min(value = 1, message = "Size must be at least 1")
        @Max(value = 100, message = "Size cannot be greater than 100")
        int size
) implements ICustomerRequest{

}
