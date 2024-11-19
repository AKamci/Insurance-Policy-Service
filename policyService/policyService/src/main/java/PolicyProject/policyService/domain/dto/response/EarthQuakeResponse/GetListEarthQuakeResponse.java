package PolicyProject.policyService.domain.dto.response.EarthQuakeResponse;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.response.IResponse.IEarthQuakeResponse;

import java.time.LocalDate;

public record GetListEarthQuakeResponse(
        Long policyId,
        LocalDate policyOfferDate,
        PolicyState state,
        String policyDescription,
        int coverage,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String tckn
) implements IEarthQuakeResponse {
}
