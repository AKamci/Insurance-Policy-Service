package PolicyProject.policyService.interfaces.mappers.PolicyMapper;

import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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



    //Hata verme ihtimali var
    List<CarPolicyModel> carPolicyEntityListToCarPolicyModelList(List<CarPolicy> carPolicyList);
    List<GetCustomerCarPoliciesResponse> customerModelToGetCarPoliciesByCustomer(List<CarPolicyModel> carPolicyModelList);



    @Mapping(source = "policyId", target = "id")
    @Mapping(source = "coverageCode", target = "coverage", qualifiedByName = "coverageFromInteger")
    CarPolicy carPolicyModelToCarPolicyEntity(CarPolicyModel carPolicyModel);

    @Named("coverageFromInteger")
    default Coverage mapIntegerToCoverage(Integer coverage) {
        if (coverage == null) {
            return null;
        }
        Coverage coverageEntity = findCoverageById(coverage);

        return coverageEntity;
    }

    default Coverage findCoverageById(Integer coverageId) {
        Coverage coverage = new Coverage();
        coverage.setId(Long.valueOf(coverageId));
        return coverage;
    }

    @Mapping(source = "id", target = "policyId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "licensePlate.plate", target = "licensePlateNumber")
    @Mapping(source = "customer.tckn", target = "tckn")
    @Mapping(source = "coverage", target = "coverage")
    CarPolicyModel carPolicyEntityToCarPolicyModel(CarPolicy carPolicy);

    @Named("coverageToInteger")
    default Integer mapCoverageToInteger(Coverage coverage) {
        if (coverage == null || coverage.getCoverageType() == null) {
            return null;
        }
        return coverage.getCoverageType().getCode();
    }
}



