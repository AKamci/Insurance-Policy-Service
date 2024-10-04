package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.carPolicyRequest.createCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.deleteCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.getCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.updateCarPolicyRequest;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.createCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.deleteCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.getCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.updateCarPolicyResponse;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

public interface CarPolicyMapper extends Mapper{

    CarPolicyMapper INSTANCE = Mappers.getMapper(CarPolicyMapper.class);

    //@Mapping(source = "customerId", target = "customerId")
    carPolicyModel createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest createCarPolicyRequest);
    carPolicyModel updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest createCarPolicyRequest);
    carPolicyModel getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest getCarPolicyRequest);
    carPolicyModel deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest deleteCarPolicyRequest);

    createCarPolicyResponse cartPolicyModelToCreateCarPolicyResponse(carPolicyModel carPolicyModel);
    updateCarPolicyResponse cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel carPolicyModel);
    getCarPolicyResponse cartPolicyModelToGetCarPolicyResponse(carPolicyModel carPolicyModel);
    deleteCarPolicyResponse cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel carPolicyModel);

    getCarPolicyResponse getCarPolicyResponse(carPolicyModel carPolicyModel);

    carPolicy carPolicyModelToCarEntity(carPolicyModel carPolicyModel);

    carPolicyModel carPolicyEntityToCarPolicyModel(Optional<carPolicy> carPolicy);

    createCarPolicyResponse carPolicyModelToCreateCarPolicyResponse(carPolicyModel carPolicyModel);
}
