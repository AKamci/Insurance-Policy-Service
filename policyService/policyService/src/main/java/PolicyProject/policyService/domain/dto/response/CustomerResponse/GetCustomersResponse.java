package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICustomerResponse;

public record GetCustomersResponse(

        Iterable<GetCustomerResponse> customersResponse

) implements ICustomerResponse {
}
