package PolicyProject.policyService.application.usecases.ExecuteWeights;
import PolicyProject.policyService.application.gateways.WeightsGateway.CarPolicyWeightGateway;
import PolicyProject.policyService.application.gateways.WeightsGateway.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.CarPolicyWeightStrategyFactory;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.EarthQuakeWeightMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ExecuteEarthQuakeWeightTest {

    @Mock
    private EarthQuakeWeightGateway weightGateway;
    @Mock
    private CarPolicyWeightsMapper carPolicyWeightsMapper;
    @Mock
    EarthQuakeWeightStrategyFactory carPolicyWeightStrategyFactory;
    @InjectMocks
    private ExecuteEarthQuakeWeight executeCarPolicyWeight;
    @BeforeEach
    void setUp() {
        executeCarPolicyWeight = new ExecuteEarthQuakeWeight(weightGateway, carPolicyWeightStrategyFactory);
    }
    @Test
    void testExecuteUpdateList_Success() {
        WeightsModel model1 = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        WeightsModel model2 = new WeightsModel(2L, "key2", new BigDecimal("20.0"), new BigDecimal("2.0"), new BigDecimal("30.0"), "type2");
        List<WeightsModel> weightsModels = List.of(model1, model2);
        EarthQaukeWeights entity1 = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model1);
        EarthQaukeWeights entity2 = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model2);
        List<EarthQaukeWeights> weightsEntities = List.of(entity1, entity2);
        when(weightGateway.updateOrSave(any())).thenReturn(weightsEntities);
        List<WeightsModel> result = executeCarPolicyWeight.executeUpdateList(weightsModels);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(weightGateway, times(1)).updateOrSave(any());
    }

    @Test
    void testExecuteUpdate_Success() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        EarthQaukeWeights entity = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model);
        when(weightGateway.update(any())).thenReturn(entity);
        WeightsModel result = executeCarPolicyWeight.executeUpdate(model);
        assertNotNull(result);
        assertEquals(model.id(), result.id());
        verify(weightGateway, times(1)).update(any());
    }
    @Test
    void testExecuteUpdate_EntityNotFoundException() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        when(weightGateway.update(any())).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeUpdate(model);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testExecuteUpdateList_EntityNotFoundException() {
        List<WeightsModel> weightsModels = List.of();
        when(weightGateway.updateOrSave(any())).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeUpdateList(weightsModels);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testExecuteGetList_Success() {
        EarthQaukeWeights entity1 = new EarthQaukeWeights(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        EarthQaukeWeights entity2 = new EarthQaukeWeights(2L, "key2", new BigDecimal("20.0"), new BigDecimal("2.0"), new BigDecimal("30.0"), "type2");
        List<EarthQaukeWeights> weightsEntities = List.of(entity1, entity2);
        when(weightGateway.listFilter()).thenReturn(weightsEntities);
        List<WeightsModel> result = executeCarPolicyWeight.executeGetList();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(weightGateway, times(1)).listFilter();
    }
    @Test
    void testExecuteGetList_EntityNotFoundException() {
        when(weightGateway.listFilter()).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeGetList();
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testExecuteDelete_Success() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        EarthQaukeWeights entity = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model);
        when(weightGateway.delete(any())).thenReturn(entity);
        WeightsModel result = executeCarPolicyWeight.executeDelete(model);
        assertNotNull(result);
        assertEquals(model.id(), result.id());
        verify(weightGateway, times(1)).delete(any());
    }
    @Test
    void testExecuteDelete_EntityNotFoundException() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        when(weightGateway.delete(any())).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeDelete(model);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testExecuteGet_Success() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        EarthQaukeWeights entity = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model);
        when(weightGateway.get(any())).thenReturn(entity);
        WeightsModel result = executeCarPolicyWeight.executeGet(model);
        assertNotNull(result);
        assertEquals(model.id(), result.id());
        verify(weightGateway, times(1)).get(any());
    }
    @Test
    void testExecuteGet_EntityNotFoundException() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        when(weightGateway.get(any())).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeGet(model);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testExecuteCreate_Success() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        EarthQaukeWeights entity = EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(model);
        when(weightGateway.create(any())).thenReturn(entity);
        WeightsModel result = executeCarPolicyWeight.executeCreate(model);
        assertNotNull(result);
        assertEquals(model.id(), result.id());
        verify(weightGateway, times(1)).create(any());
    }
    @Test
    void testExecuteCreate_EntityNotFoundException() {
        WeightsModel model = new WeightsModel(1L, "key1", new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1");
        when(weightGateway.create(any())).thenReturn(null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            executeCarPolicyWeight.executeCreate(model);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testGet_ALicensePlateModel_Success() {
        HouseModel houseModel = new HouseModel(null,null,null,null,
                null,null,null,null);        when(weightGateway.list()).thenReturn(List.of(new EarthQaukeWeights(1L, "key1", new BigDecimal("2.0"), new BigDecimal("1.0"), new BigDecimal("10.0"), "constant")));
        HouseModel result = executeCarPolicyWeight.Get_AHouseModel(houseModel);
        assertNotNull(result);
        assertEquals(2L, result.Amount());
    }
    @Test
    void testGet_ALicensePlateModel_EntityNotFoundException() {
        HouseModel houseModel = new HouseModel(null,null,null,null,
                null,null,null,null);
        when(weightGateway.list()).thenReturn(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            executeCarPolicyWeight.Get_AHouseModel(houseModel);
        });
        String expectedMessage = "Entity not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}