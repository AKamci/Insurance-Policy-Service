package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

public record GetCustomerCarPoliciesRequest(

        Long customerId

) implements ICarPolicyRequest{
}
