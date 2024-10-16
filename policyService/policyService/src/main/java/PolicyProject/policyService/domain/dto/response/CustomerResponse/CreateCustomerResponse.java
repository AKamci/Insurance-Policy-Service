package PolicyProject.policyService.domain.dto.response.CustomerResponse;

public record CreateCustomerResponse(

    String tckn,
    Long id


)
   implements ICustomerResponse
{
}
