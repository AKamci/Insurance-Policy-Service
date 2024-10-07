package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCarPoliciesByCustomer;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.createCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.deleteCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.getCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.updateCarPolicyResponse;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
@org.mapstruct.Mapper(componentModel = "spring")
public interface CarPolicyMapper {

    CarPolicyMapper INSTANCE = Mappers.getMapper(CarPolicyMapper.class);

    carPolicyModel createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest createCarPolicyRequest);
    carPolicyModel updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest createCarPolicyRequest);
    carPolicyModel getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest getCarPolicyRequest);
    carPolicyModel deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest deleteCarPolicyRequest);
    carPolicyModel getCustomerCarPoliciesToCarPolicyModel(getCustomerCarPoliciesRequest getCustomerCarPoliciesRequest);

    createCarPolicyResponse carPolicyModelToCreateCarPolicyResponse(carPolicyModel carPolicyModel);
    updateCarPolicyResponse cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel carPolicyModel);
    getCarPolicyResponse cartPolicyModelToGetCarPolicyResponse(carPolicyModel carPolicyModel);
    deleteCarPolicyResponse cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel carPolicyModel);


    List<carPolicyModel> carPolicyEntityListToCarPolicyModelList(List<CarPolicy> carPolicies);

    List<getCarPolicyResponse> customerModelToGetCarPoliciesByCustomer(List<carPolicyModel> carPolicyModelList);



    CarPolicy carPolicyModelToCarPolicyEntity(carPolicyModel carPolicyModel);

    carPolicyModel carPolicyEntityToCarPolicyModel(CarPolicy carPolicy);


}
