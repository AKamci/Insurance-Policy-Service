package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import java.util.List;

public record UpdateCustomerRequest(

        Long id,
        String name,
        String address,
        String phone,
        String email,
        String password,
        int age,
        String gender,

        List<CarPolicy> carPolicies



) implements ICustomerRequest{
}
