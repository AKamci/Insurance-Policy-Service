package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;

import java.util.concurrent.CompletableFuture;

public interface ICustomerService extends IBaseService<
        CompletableFuture<CreateCustomerResponse>,
        CompletableFuture<UpdateCustomerResponse>,
        CompletableFuture<DeleteCustomerResponse>,
        CompletableFuture<GetCustomerResponse>,
        CompletableFuture<GetCustomerResponse>,
        CustomerModel
        > {
}