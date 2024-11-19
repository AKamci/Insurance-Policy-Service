package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;

public interface ICarPolicyService extends IBaseService<
        CreateCarPolicyResponse,
        UpdateCarPolicyResponse,
        DeleteCarPolicyResponse,
        GetCarPolicyResponse,
        GetCarPolicyResponse,
        CarPolicyModel
        > {
}