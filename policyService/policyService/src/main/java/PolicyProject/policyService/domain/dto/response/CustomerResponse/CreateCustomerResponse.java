package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICustomerResponse;

public record CreateCustomerResponse(

    String tckn,
    Long id


)
   implements ICustomerResponse
{
}
