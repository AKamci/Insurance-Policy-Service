package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record GetCarPolicyListRequest(

        int size,
        int page,
        Long policyId,
        LocalDate policyOfferDate,
        String policyDescription,
        Integer policyType,
        CarPolicyState state,
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate

) implements ICarPolicyRequest {
}
