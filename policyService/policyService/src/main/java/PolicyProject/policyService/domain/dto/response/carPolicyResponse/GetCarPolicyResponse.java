package PolicyProject.policyService.domain.dto.response.carPolicyResponse;

import java.sql.Date;
import java.time.LocalDate;

public record GetCarPolicyResponse(

        Long policyId,
        LocalDate policyOfferDate,
        Boolean policyStatus,
        String policyDescription,
        String policyType,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String licensePlateNumber,
        String tckn
) implements ICarPolicyResponse {
}
