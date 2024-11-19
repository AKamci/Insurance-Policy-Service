package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.ICustomerResponse;

public record UpdateCustomerResponse(


        String tckn


) implements ICustomerResponse {
}
