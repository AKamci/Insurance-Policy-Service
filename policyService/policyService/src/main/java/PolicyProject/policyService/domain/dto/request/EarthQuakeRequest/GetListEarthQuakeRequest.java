package PolicyProject.policyService.domain.dto.request.EarthQuakeRequest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;

import java.time.LocalDate;

public record GetListEarthQuakeRequest(
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
        LocalDate policyEndDate,


        Integer number,
        Integer apartmentNumber,
        String city,
        String district,
        String neighborhood


) implements IEarthQuakeRequest{
}
