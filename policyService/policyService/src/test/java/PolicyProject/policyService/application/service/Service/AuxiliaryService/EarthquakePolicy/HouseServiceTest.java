package PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy;

import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy.ExecuteHouse;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {

    @Mock
    private ExecuteHouse executeHouse;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private HouseMapper houseMapper;

    @Mock
    private HouseModel houseModel;


    @InjectMocks
    private HouseService houseService;


    public HouseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWCustomer_ValidHouseModel_ReturnsGetHouseWCustomerResponse() {
        objectValidation.validateModel(houseModel, "houseModel");
        houseMapper.HouseModelToGetHouseWCustomerResponse(houseModel);
        executeHouse.ExecuteGetHouse(houseModel);
        verify(executeHouse,times(1)).ExecuteGetHouse(houseModel);
        verify(houseMapper,times(1)).HouseModelToGetHouseWCustomerResponse(any(HouseModel.class));
        verify(objectValidation, times(1)).validateModel(any(HouseModel.class), any(String.class));
    }
}