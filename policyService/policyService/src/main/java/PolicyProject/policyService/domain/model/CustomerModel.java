package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import java.time.LocalDate;
import java.util.List;

public record CustomerModel(

        Long id,
        String name,
        String tckn,
        String address,
        String phone,
        String email,
        LocalDate birthDay,
        int grade,
        Integer gender,
        int page,
        int size,

        List<CarPolicy> carPolicies


) {
}
