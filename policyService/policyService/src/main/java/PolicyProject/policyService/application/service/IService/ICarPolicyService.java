package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;

import java.util.concurrent.CompletableFuture;

public interface ICarPolicyService extends IBaseService<
        CreateCarPolicyResponse,
        UpdateCarPolicyResponse,
        DeleteCarPolicyResponse,
        GetCarPolicyResponse,
        GetCarPolicyResponse,
        CarPolicyModel
        > {
}