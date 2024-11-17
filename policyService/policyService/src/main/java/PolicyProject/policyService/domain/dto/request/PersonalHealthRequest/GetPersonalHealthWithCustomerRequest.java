package PolicyProject.policyService.domain.dto.request.PersonalHealthRequest;

public record GetPersonalHealthWithCustomerRequest(
        String tckn,
        Integer coverageCode
) {
}
