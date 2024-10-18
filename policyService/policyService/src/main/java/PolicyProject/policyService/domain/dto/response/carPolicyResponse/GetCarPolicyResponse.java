package PolicyProject.policyService.domain.dto.response.carPolicyResponse;

import java.sql.Date;
import java.time.LocalDate;

public record GetCarPolicyResponse(

        Long id,
        String policyName,
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
