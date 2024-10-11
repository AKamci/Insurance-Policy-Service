package PolicyProject.policyService.domain.dto.request.CustomerRequest;

public record GetCustomerRequest(
        String tckn
) implements ICustomerRequest{
}
