package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICustomerResponse;

import java.time.LocalDate;

public record GetCustomerResponse(


        Long id,
        String name,
        String tckn,
        String address,
        String phone,
        String email,
        LocalDate birthDay,
        Integer gender


) implements ICustomerResponse {
}
