package PolicyProject.policyService.domain.dto.response.EarthQuakeResponse;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;

import java.time.LocalDate;

public record GetEarthQuakeResponse(

        Long policyId,
        LocalDate policyOfferDate,
        CarPolicyState state,
        String policyDescription,
        Coverage coverage,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String tckn
) implements IEarthQuakeResponse{
}