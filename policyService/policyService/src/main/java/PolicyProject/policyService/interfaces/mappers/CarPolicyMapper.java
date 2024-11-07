package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CarPolicyMapper {

    CarPolicyMapper INSTANCE = Mappers.getMapper(CarPolicyMapper.class);

    CarPolicyModel createCarPolicyRequestToCarPolicyModel(CreateCarPolicyRequest createCarPolicyRequest);
    CarPolicyModel updateCarPolicyRequestToCarPolicyModel(UpdateCarPolicyRequest createCarPolicyRequest);
    CarPolicyModel setStateCarPolicyRequestToCarPolicyModel(SetCarPolicyStatusRequest createCarPolicyRequest);

    CarPolicyModel getCarPolicyRequestTocarPolicyModel(GetCarPolicyRequest getCarPolicyRequest);
    CarPolicyModel getCarPolicyRequestWPlateTocarPolicyModel(GetCarPolicyWPlateRequest getCarPolicyWPlateRequest);
    CarPolicyModel deleteCarPolicyRequestToCarPolicyModel(DeleteCarPolicyRequest deleteCarPolicyRequest);
    CarPolicyModel getCustomerCarPoliciesToCarPolicyModel(GetCustomerCarPoliciesRequest getCustomerCarPoliciesRequest);
    CarPolicyModel getCustomerCarPoliciesBetweenDateToCarPolicyModel(GetCarPolicyBetweenDateRequest getCarPolicyBetweenDateRequest);
    CarPolicyModel getCarPoliciesToCarPolicyModel(GetCarPolicyListRequest getCarPolicyListRequest);


    CreateCarPolicyResponse carPolicyModelToCreateCarPolicyResponse(CarPolicyModel carPolicyModel);
    UpdateCarPolicyResponse cartPolicyModelToUpdateCarPolicyResponse(CarPolicyModel carPolicyModel);
    SetCarPolicyStatusResponse cartPolicyModelToSetStateCarPolicyResponse(CarPolicyModel carPolicyModel);
    GetCarPolicyResponse cartPolicyModelToGetCarPolicyResponse(CarPolicyModel carPolicyModel);
    List<GetCarPolicyResponse> cartPolicyModelListToGetCarPolicyResponseList(List<CarPolicyModel> carPolicyModel);
    DeleteCarPolicyResponse cartPolicyModelToDeleteCarPolicyResponse(CarPolicyModel carPolicyModel);



    List<CarPolicyModel> carPolicyEntityListToCarPolicyModelList(List<CarPolicy> carPolicies);


    List<GetCustomerCarPoliciesResponse> customerModelToGetCarPoliciesByCustomer(List<CarPolicyModel> carPolicyModelList);

    @Mapping(source = "policyId", target = "id")
    CarPolicy carPolicyModelToCarPolicyEntity(CarPolicyModel carPolicyModel);

    CarPolicy CarPolicyToCarPolicy(CarPolicy carPolicy);

    //CarPolicy carPolicyModelToCarPolicyEntity(CarPolicyModel carPolicyModel);
    @Mapping(source = "id", target = "policyId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "licensePlate.plate", target = "licensePlateNumber")
    @Mapping(source = "customer.tckn", target = "tckn")
    CarPolicyModel carPolicyEntityToCarPolicyModel(CarPolicy carPolicy);


}
