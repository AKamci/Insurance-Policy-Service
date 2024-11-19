package PolicyProject.policyService.domain.model.PolicyModel;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record HealthPolicyModel(
        Long policyId,
        Long personalHealthId,
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
        Boolean hasPreviousSurgeries,
        LocalDate policyOfferDate, //
        String policyDescription, //
        Double policyAmount,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        PolicyState state,
        int page,
        int size

        ) {}