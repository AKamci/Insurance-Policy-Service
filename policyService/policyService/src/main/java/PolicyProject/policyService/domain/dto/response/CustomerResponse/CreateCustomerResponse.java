package PolicyProject.policyService.domain.dto.response.CustomerResponse;

public record CreateCustomerResponse(

    Long id,
    String email


)
   implements ICustomerResponse
{
}
