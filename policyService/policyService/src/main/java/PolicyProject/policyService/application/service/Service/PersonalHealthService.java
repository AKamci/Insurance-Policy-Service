package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecutePersonalHealth;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.PersonalHealthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonalHealthService {

    private final ExecutePersonalHealth executePersonalHealth;
    private final ObjectValidation objectValidation;


    public GetPersonalHealthWithCustomerResponse getWCustomer(PersonalHealthModel personalHealthModel)
    {
        PersonalHealthModel personalHealthModelResult = executePersonalHealth.ExecuteGetWithCustomer(personalHealthModel);
        return PersonalHealthMapper.INSTANCE.getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(personalHealthModelResult);
    }
}
