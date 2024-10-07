package PolicyProject.policyService.domain.dto.request.CustomerRequest;


import java.util.List;

public record CreateCustomerRequest(

        String name,
        String address,
        String phone,
        String email,
        String password,
        int age,
        String gender

) implements ICustomerRequest{
}
