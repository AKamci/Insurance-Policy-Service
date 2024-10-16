package PolicyProject.policyService.domain.dto.request.CustomerRequest;

public record DeleteCustomerRequest(
       String tckn
)implements ICustomerRequest {
}
