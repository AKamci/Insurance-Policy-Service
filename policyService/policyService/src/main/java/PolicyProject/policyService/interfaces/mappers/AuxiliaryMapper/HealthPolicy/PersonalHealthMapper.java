package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy;

import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.CreatePersonalHealthRequest;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.CreatePersonalHealthResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper()

public interface PersonalHealthMapper {

    PersonalHealthMapper INSTANCE = Mappers.getMapper(PersonalHealthMapper.class);

    PersonalHealthModel getGetPersonalHealthWithCustomerRequestToPersonalHealthModel(GetPersonalHealthWithCustomerRequest getPersonalHealthWithCustomerRequest);
    PersonalHealthModel createPersonalHealthRequestToPersonalHealthModel(CreatePersonalHealthRequest createPersonalHealthRequest);

    GetPersonalHealthWithCustomerResponse getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(PersonalHealthModel personalHealthModel);
    CreatePersonalHealthResponse CreatePersonalHealthModelToPersonalHealthResponse(PersonalHealthModel personalHealthModel);

    @Mapping(source = "tckn", target = "customer.tckn")
    PersonalHealth getPersonalHealthModelToPersonalHealthEntity(PersonalHealthModel personalHealthModel);

    @Mapping(source = "customer.tckn", target = "tckn")
    PersonalHealthModel getPersonalHealthEntityToPersonalHealthModel(PersonalHealth personalHealth);




}
