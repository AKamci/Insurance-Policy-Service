package PolicyProject.policyService.domain.dto.response.HealthPolicyResponse;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.response.IResponse.IHealthPolicyResponse;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record GetHealthPolicyResponse(
        Long policyId,
        LocalDate policyOfferDate,
        PolicyState state,
        String policyDescription,
        Coverage coverage,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String tckn,

        BloodType bloodType
) implements IHealthPolicyResponse {
}
