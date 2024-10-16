package PolicyProject.policyService.domain.dto.request.CustomerRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record UpdateCustomerRequest(

        String tckn,
        String name,
        String address,
        String phone,
        String email,
        String password,
        LocalDate birthDay,
        String gender,

        List<CarPolicy> carPolicies



) implements ICustomerRequest{
}
