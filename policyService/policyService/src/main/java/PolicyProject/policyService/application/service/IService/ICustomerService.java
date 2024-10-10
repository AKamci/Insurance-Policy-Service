package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;

public interface ICustomerService extends IBaseService<
        CreateCustomerResponse,
        UpdateCustomerResponse,
        DeleteCustomerResponse,
        GetCustomerResponse,
        GetCustomerResponse,
        CustomerModel
        > {
    // Ek metodlar varsa burada tanımlanabilir
}