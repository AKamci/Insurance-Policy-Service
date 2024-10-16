package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CustomerModel(

        Long id,
        String name,
        String tckn,
        String address,
        String phone,
        String email,
        String password,
        LocalDate birthDay,
        String gender,

        List<CarPolicy> carPolicies


) {
}
