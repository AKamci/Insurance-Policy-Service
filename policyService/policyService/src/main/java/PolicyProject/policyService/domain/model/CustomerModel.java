package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.List;

public record CustomerModel(

         Long id,
         String name,
         String address,
         String phone,
         String email,
         String password,
         int age,
         String gender,

         List<CarPolicy> carPolicies


) {
}
