package PolicyProject.policyService.domain.dto.request.LicensePlateRequest;

import java.time.LocalDate;

public record GetPlateWithCustomerRequest(

        String plate,
        String policyType,
        LocalDate policyStartDate,
        LocalDate policyEndDate



) {
}
