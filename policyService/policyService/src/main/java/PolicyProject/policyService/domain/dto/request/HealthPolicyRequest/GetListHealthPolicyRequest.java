package PolicyProject.policyService.domain.dto.request.HealthPolicyRequest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;

import java.time.LocalDate;

public record GetListHealthPolicyRequest(

        int size,
        int page,
        Long policyId,
        LocalDate policyOfferDate,
        String policyDescription,
        Integer coverageCode,
        PolicyState state,
        Double policyAmount,
        Long customerId,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate



) implements IHealthPolicyRequest{
}
