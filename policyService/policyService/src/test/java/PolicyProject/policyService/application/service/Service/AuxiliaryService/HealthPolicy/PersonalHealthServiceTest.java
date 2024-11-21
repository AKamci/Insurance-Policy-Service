package PolicyProject.policyService.application.service.Service.AuxiliaryService.HealthPolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy.HouseService;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy.ExecutePersonalHealth;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class PersonalHealthServiceTest {

    @Mock
    private ExecutePersonalHealth executePersonalHealth;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private PersonalHealthMapper personalHealthMapper;

    @Mock
    private PersonalHealthModel personalHealthModel;

    @InjectMocks
    private PersonalHealthService personalHealthService;


    public PersonalHealthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWCustomer_ValidHouseModel_ReturnsGetHouseWCustomerResponse() {
        objectValidation.validateModel(personalHealthModel, "personalHealthModel");
        personalHealthMapper.getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(personalHealthModel);
        executePersonalHealth.ExecuteGetPersonalHealth(personalHealthModel);

        verify(executePersonalHealth,times(1)).ExecuteGetPersonalHealth(personalHealthModel);
        verify(personalHealthMapper,times(1)).getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(personalHealthModel);
        verify(objectValidation, times(1)).validateModel(any(PersonalHealthModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidHouseModel_ReturnsCreatePersonalHealthResponse() {
        objectValidation.validateModel(personalHealthModel, "personalHealthModel");
        personalHealthMapper.CreatePersonalHealthModelToPersonalHealthResponse(personalHealthModel);
        executePersonalHealth.ExecuteCreate(personalHealthModel);

        verify(executePersonalHealth,times(1)).ExecuteCreate(personalHealthModel);
        verify(personalHealthMapper,times(1)).CreatePersonalHealthModelToPersonalHealthResponse(personalHealthModel);
        verify(objectValidation, times(1)).validateModel(any(PersonalHealthModel.class), any(String.class));
    }
}
