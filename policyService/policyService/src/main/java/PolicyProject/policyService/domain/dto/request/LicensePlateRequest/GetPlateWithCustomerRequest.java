package PolicyProject.policyService.domain.dto.request.LicensePlateRequest;

import java.time.LocalDate;

public record GetPlateWithCustomerRequest(

        String plate,
        int policyType,
        LocalDate policyStartDate,
        LocalDate policyEndDate



) {
}
