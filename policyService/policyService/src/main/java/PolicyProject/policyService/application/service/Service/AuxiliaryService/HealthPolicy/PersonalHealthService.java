package PolicyProject.policyService.application.service.Service.AuxiliaryService.HealthPolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy.ExecutePersonalHealth;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.CreatePersonalHealthResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonalHealthService {

    private final ExecutePersonalHealth executePersonalHealth;
    private final ObjectValidation objectValidation;


    public GetPersonalHealthWithCustomerResponse getWCustomer(PersonalHealthModel personalHealthModel)
    {
        objectValidation.validateModel(personalHealthModel, "personalHealthModel");
        return PersonalHealthMapper.INSTANCE.getPersonalHealthModelToGetPersonalHealthWithCustomerResponse
                (executePersonalHealth.ExecuteGetWithCustomer(personalHealthModel));
    }

    public CreatePersonalHealthResponse create(PersonalHealthModel personalHealthModel)
    {
        objectValidation.validateModel(personalHealthModel, "personalHealthModel");
        return PersonalHealthMapper.INSTANCE.CreatePersonalHealthModelToPersonalHealthResponse
                (executePersonalHealth.ExecuteCreate(personalHealthModel));
    }

}
