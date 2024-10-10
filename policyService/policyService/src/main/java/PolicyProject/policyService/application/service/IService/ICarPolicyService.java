package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.request.carPolicyRequest.ICarPolicyRequest;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.CreateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.DeleteCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.UpdateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

public interface ICarPolicyService extends IBaseService<
        createCarPolicyResponse,
        updateCarPolicyResponse,
        deleteCarPolicyResponse,
        getCarPolicyResponse,
        getCarPolicyResponse,
        carPolicyModel
        > {
    // Ek metodlar varsa burada tanımlanabilir
}