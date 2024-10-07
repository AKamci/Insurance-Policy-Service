package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import java.util.List;

public record GetCustomersResponse(

        Iterable<GetCustomerResponse> customersResponse

) implements ICustomerResponse{
}
