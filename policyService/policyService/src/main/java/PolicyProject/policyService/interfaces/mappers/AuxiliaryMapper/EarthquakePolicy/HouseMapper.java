package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy;

import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring")
public interface HouseMapper {

    HouseMapper INSTANCE = Mappers.getMapper(HouseMapper.class);


    @Mapping(source = "apartmentNumber", target = "building.apartmentNumber")
    @Mapping(source = "city", target = "building.address.city")
    @Mapping(source = "district", target = "building.address.district")
    @Mapping(source = "neighborhood", target = "building.address.neighborhood")
    @Mapping(source = "coverageCode", target = "coverageCode")
    HouseModel getHouseWithCustomerRequestToHouseModel(GetHouseWCustomerRequest getHouseWCustomerRequest);

    GetHouseWCustomerResponse HouseModelToGetHouseWCustomerResponse(HouseModel houseModel);

    House HouseModelToHouseEntity(HouseModel houseModel);


    HouseModel houseEntityToHouseModel(House house);




}
