package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;

import java.util.concurrent.CompletableFuture;

public interface ICarPolicyService extends IBaseService<
        CompletableFuture<CreateCarPolicyResponse>,
        CompletableFuture<UpdateCarPolicyResponse>,
        CompletableFuture<DeleteCarPolicyResponse>,

        CompletableFuture<GetCarPolicyResponse>,
        CompletableFuture<GetCarPolicyResponse>,
        CarPolicyModel
                > {
    // Ek metodlar varsa burada tanımlanabilir
}