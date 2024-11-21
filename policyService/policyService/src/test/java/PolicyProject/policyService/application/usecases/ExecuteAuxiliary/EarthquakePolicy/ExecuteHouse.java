//package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy;
//
//import PolicyProject.policyService.application.gateways.AuxiliaryGateway.EarthquakePolicy.HouseGateway;
//import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
//import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteEarthQuakeWeight;
//import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
//import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
//import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
//import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ExecuteHouseTest {
//
//    @Mock
//    private HouseGateway houseGateway;
//
//    @Mock
//    private ExecuteEarthQuakeWeight executeWeight;
//
//    @InjectMocks
//    private ExecuteHouseTest executeHouseTest;
//
//    private HouseModel houseModel;
//
//    @BeforeEach
//    void setUp() {
//        houseModel = new HouseModel();
//        houseModel.setId(1L);
//        houseModel.setCoverageCode("testCoverageCode");
//    }
//
//    @Test
//    public void testExecuteGetWithCustomer_HouseEntityFound() {
//        House houseEntity = new House();
//        HouseModel newHouseModel = new HouseModel();
//        HouseModel finalHouseModel = new HouseModel();
//
//        when(houseGateway.getWCustomer(any(House.class))).thenReturn(houseEntity);
//        when(HouseMapper.INSTANCE.houseEntityToHouseModel(any(House.class))).thenReturn(newHouseModel);
//        when(executeWeight.Get_AHouseModel(any(HouseModel.class))).thenReturn(finalHouseModel);
//
//        HouseModel result = executeHouseTest.ExecuteGetWithCustomer(houseModel);
//
//        assertEquals(finalHouseModel, result);
//    }
//
//    @Test
//    public void testExecuteGetWithCustomer_HouseEntityNotFound() {
//        when(houseGateway.getWCustomer(any(House.class))).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class, () -> executeHouseTest.ExecuteGetWithCustomer(houseModel));
//    }
//
//    @Test
//    public void testExecuteGetHouse_HouseEntityFound() {
//        House houseEntity = new House();
//        HouseModel newHouseModel = new HouseModel();
//
//        when(houseGateway.get(any(House.class))).thenReturn(houseEntity);
//        when(HouseMapper.INSTANCE.houseEntityToHouseModel(any(House.class))).thenReturn(newHouseModel);
//
//        HouseModel result = executeHouseTest.ExecuteGetHouse(houseModel);
//
//        assertEquals(newHouseModel, result);
//    }
//
//    @Test
//    public void testExecuteGetHouse_HouseEntityNotFound() {
//        when(houseGateway.get(any(House.class))).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class, () -> executeHouseTest.ExecuteGetHouse(houseModel));
//    }
//}