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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;


public class PersonalHealthServiceTest {

    @Mock
    private ExecutePersonalHealth executePersonalHealth;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private PersonalHealthModel personalHealthModel;

    @InjectMocks
    private PersonalHealthService personalHealthService;


    public PersonalHealthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWCustomer_ValidHouseModel_ReturnsGetHouseWCustomerResponse() {
        when(executePersonalHealth.ExecuteGetWithCustomer(personalHealthModel)).thenReturn(personalHealthModel);
        doNothing().when(objectValidation).validateModel(personalHealthModel, "personalHealthModel");

        personalHealthService.getWCustomer(personalHealthModel);

        verify(executePersonalHealth,times(1)).ExecuteGetWithCustomer(personalHealthModel);
        verify(objectValidation, times(1)).validateModel(any(PersonalHealthModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidHouseModel_ReturnsCreatePersonalHealthResponse() {
        when(executePersonalHealth.ExecuteCreate(personalHealthModel)).thenReturn(personalHealthModel);
        doNothing().when(objectValidation).validateModel(personalHealthModel, "personalHealthModel");

        personalHealthService.create(personalHealthModel);

        verify(executePersonalHealth,times(1)).ExecuteCreate(personalHealthModel);
        verify(objectValidation, times(1)).validateModel(any(PersonalHealthModel.class), any(String.class));
    }
}
