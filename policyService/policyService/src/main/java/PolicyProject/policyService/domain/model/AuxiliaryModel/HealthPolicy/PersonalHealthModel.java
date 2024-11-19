package PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record PersonalHealthModel(

        Long id,
        String tckn,
        Coverage coverage,
        Integer coverageCode,
        Customer customer,
        Integer height,
        Double weight,
        Double bmi,
        @Enumerated(EnumType.ORDINAL)
        BloodType bloodType,
        Boolean alcoholConsumption,
        Boolean smokeConsumption,
        Boolean isPregnant,
        Boolean hasDisability,
        Boolean hasPreviousSurgeries,
        Long Amount

) {
}
