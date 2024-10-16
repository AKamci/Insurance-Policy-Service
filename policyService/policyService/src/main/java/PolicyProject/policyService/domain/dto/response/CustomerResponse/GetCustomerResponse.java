package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record GetCustomerResponse(


        Long id,
        String name,
        String tckn,
        String address,
        String phone,
        String email,
        LocalDate birthDay,
        String gender


) implements ICustomerResponse{
}
