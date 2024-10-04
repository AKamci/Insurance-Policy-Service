package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.request.CustomerRequest.ICustomerRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.ICarPolicyRequest;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public interface ICustomerService extends IBaseService<CustomerModel, ICustomerRequest> {
}
