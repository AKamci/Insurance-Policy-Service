package PolicyProject.policyService.domain.dto.response.EarthQuakeResponse;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;

import java.time.LocalDate;

public record GetEarthQuakeResponse(

        Long policyId,
        LocalDate policyOfferDate,
        CarPolicyState state,
        String policyDescription,
        int coverage,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String tckn
) implements IEarthQuakeResponse{
}
