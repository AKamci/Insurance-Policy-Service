package PolicyProject.policyService.domain.dto.request.CustomerRequest;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CreateCustomerRequest(

        String name,
        String tckn,
        String address,
        String phone,
        String email,
        String password,
        LocalDate birthDay,
        String gender

) implements ICustomerRequest{
}
