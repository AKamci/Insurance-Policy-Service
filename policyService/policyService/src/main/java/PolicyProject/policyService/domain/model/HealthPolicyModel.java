package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.BloodType;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import jakarta.persistence.*;

public record HealthPolicyModel(
        Long policyId,
        Long customerId,
        String tckn,
        Double height,
        Double weight,
        Integer coverageCode,
        Coverage coverage,
        Double bmi,
        BloodType bloodType,
        Boolean alcoholConsumption,
        Boolean smokeConsumption,
        Boolean isPregnant,
        Boolean hasDisability,
        Boolean hasPreviousSurgeries
) {}