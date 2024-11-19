package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.IRequest.IHealthPolicyRequest;

import java.time.LocalDate;

public record GetListHealthPolicyRequest(

        Long policyId,
        Long personalHealthId,
        String tckn,
        Double height,
        Double weight,
        Integer coverageCode,
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


) implements IHealthPolicyRequest {
}
