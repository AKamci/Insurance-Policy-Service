package PolicyProject.policyService.domain.dto.response.carPolicyResponse;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;

import java.sql.Date;
import java.time.LocalDate;

public record GetCarPolicyResponse(

        Long policyId,
        LocalDate policyOfferDate,
        PolicyState state,
        String policyDescription,
        Coverage coverage,
        Double policyAmount,
        Long customerId,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        String licensePlateNumber,
        String tckn
) implements ICarPolicyResponse {
}
