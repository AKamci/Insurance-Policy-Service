package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

import java.util.List;

public record GetCustomerResponse(


        Long id,
        String name,
        String address,
        String phone,
        String email,
        int age,
        String gender,

        List<CarPolicy> carPolicies


) implements ICustomerResponse{
}
