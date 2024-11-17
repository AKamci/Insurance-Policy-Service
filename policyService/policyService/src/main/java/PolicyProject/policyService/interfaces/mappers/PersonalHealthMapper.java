package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper()

public interface PersonalHealthMapper {

    PersonalHealthMapper INSTANCE = Mappers.getMapper(PersonalHealthMapper.class);

    PersonalHealthModel getGetPersonalHealthWithCustomerRequestToPersonalHealthModel(GetPersonalHealthWithCustomerRequest getPersonalHealthWithCustomerRequest);

    GetPersonalHealthWithCustomerResponse getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(PersonalHealthModel personalHealthModel);

    @Mapping(source = "tckn", target = "customer.tckn")
    PersonalHealth getPersonalHealthModelToPersonalHealthEntity(PersonalHealthModel personalHealthModel);

    PersonalHealthModel getPersonalHealthEntityToPersonalHealthModel(PersonalHealth personalHealth);




}
