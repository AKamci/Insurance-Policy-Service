package PolicyProject.policyService.domain.dto.request.CarPolicyRequest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.IRequest.ICarPolicyRequest;

import java.time.LocalDate;

public record GetCarPolicyListRequest(

        int size,
        int page,
        Long policyId,
        LocalDate policyOfferDate,
        String policyDescription,
        Integer coverageCode,
        PolicyState state,
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate

) implements ICarPolicyRequest {
}
