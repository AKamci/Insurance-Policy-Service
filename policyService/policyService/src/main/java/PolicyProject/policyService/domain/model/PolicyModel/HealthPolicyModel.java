package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;

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