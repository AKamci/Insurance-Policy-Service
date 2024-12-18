package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy;

import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper()
public interface LicensePlateMapper {

    LicensePlateMapper INSTANCE = Mappers.getMapper(LicensePlateMapper.class);


    LicensePlateModel getPlateWithCustomerRequestToLicensePlateModel(GetPlateWithCustomerRequest getPlateWithCustomerRequest);
    GetPlateWithCustomerResponse LicensePlateModelToGetPlateWithCustomerResponse(LicensePlateModel licensePlateModel);

//    @Mapping(source = "customer", target = "licensePlate.customer")
//    @Mapping(source = "car", target = "licensePlate.car")
    LicensePlate LicensePlateModelToCustomerEntity(LicensePlateModel licensePlateModel);


    @Mapping(source = "licensePlate.customer", target = "customer")
    @Mapping(source = "licensePlate.car", target = "car")
    LicensePlateModel licensePlateEntityToLicensePlateModel(LicensePlate licensePlate);

}
