package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.CreateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.DeleteCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.UpdateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;

public interface IWeightService extends ISimpleCRUDService<
        CreateWeightResponse,
        UpdateWeightResponse,
        DeleteWeightResponse,
        GetWeightResponse,
        GetWeightResponse,
        WeightsModel
        > {
}
