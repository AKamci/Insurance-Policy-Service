package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.EarthquakePolicy.HouseGateway;
import PolicyProject.policyService.application.gateways.WeightsGateway.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExecuteHouseTest {

    @Mock
    private HouseGateway houseGateway;

    @Mock
    private EarthQuakeWeightGateway earthQuakeWeightGateway;

    @Mock
    private EarthQuakeWeightStrategyFactory earthQuakeWeightStrategyFactory;

    @InjectMocks
    private ExecuteEarthQuakeWeight executeEarthQuakeWeight;

    @InjectMocks
    private ExecuteHouse executeHouse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        executeEarthQuakeWeight = new ExecuteEarthQuakeWeight(earthQuakeWeightGateway, earthQuakeWeightStrategyFactory);
        executeHouse = new ExecuteHouse(houseGateway, executeEarthQuakeWeight);
    }

    @Test
    void testExecuteGetHouse_Success() {
        HouseModel houseModel = new HouseModel(1L, 1, 1, 100, null, null, "12345678901", 1000L);
        House houseEntity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);

        when(houseGateway.get(any(House.class))).thenReturn(houseEntity);

        HouseModel result = executeHouse.ExecuteGetHouse(houseModel);

        assertNotNull(result);
        assertEquals(houseModel.id(), result.id());
    }

    @Test
    void testExecuteGetWithCustomer_Success() {
        HouseModel houseModel = new HouseModel(1L, 1, 1, 100, null, null, "12345678901", null);
        House houseEntity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);
        HouseModel newHouseModel = new HouseModel(1L, 1, 1, 100, null, null, "12345678901", 1500L);

        when(houseGateway.getWCustomer(eq(HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel)))).thenReturn(houseEntity);
        when(executeEarthQuakeWeight.Get_AHouseModel(any(HouseModel.class))).thenReturn(newHouseModel);

        HouseModel result = executeHouse.ExecuteGetWithCustomer(houseModel);

        // Doğrulama
        assertNotNull(result);
        assertEquals(newHouseModel.id(), result.id());
        assertEquals(newHouseModel.Amount(), result.Amount());
    }

    @Test
    void testExecuteGetWithCustomer_EntityNotFound() {
        HouseModel houseModel = new HouseModel(1L, 1, 1, 100, null, null, "12345678901", 1000L);

        when(houseGateway.getWCustomer(any(House.class))).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> executeHouse.ExecuteGetWithCustomer(houseModel));

        assertEquals("Entity not found", thrown.getMessage());
    }

    @Test
    void testExecuteGetHouse_EntityNotFound() {
        HouseModel houseModel = new HouseModel(1L, 1, 1, 100, null, null, "12345678901", 1000L);

        when(houseGateway.get(any(House.class))).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> executeHouse.ExecuteGetHouse(houseModel));

        assertEquals("Entity not found", thrown.getMessage());
    }

}
