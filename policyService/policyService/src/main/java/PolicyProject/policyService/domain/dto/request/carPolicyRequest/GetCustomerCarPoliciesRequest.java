package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

public record GetCustomerCarPoliciesRequest(

        String tckn

) implements ICarPolicyRequest{
}
