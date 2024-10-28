package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record GetCarPolicyListRequest(

        int size,
        int page,
        Long policyId,
        LocalDate policyOfferDate,
        String policyDescription,
        String policyType,
        Boolean policyStatus,
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate

) implements ICarPolicyRequest {
}
