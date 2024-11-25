package PolicyProject.policyService.application.usecases.ExecutePolicy;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CarPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import PolicyProject.policyService.application.service.ModelFactory.CustomerModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExecuteCarPolicyTest {

    @Test
    void testExecuteGetTotalRecord_Success() {
        given(carPolicyGateway.getTotal()).willReturn(10);

        int totalRecords = executeCarPolicy.executeGetTotalRecord();

        assertEquals(10, totalRecords);
        verify(carPolicyGateway, times(1)).getTotal();
    }

    @Test
    void testExecuteGetTotalRecord_EntityNotFoundException() {
        given(carPolicyGateway.getTotal()).willReturn(0);

        int totalRecords = executeCarPolicy.executeGetTotalRecord();

        assertEquals(0, totalRecords);
        verify(carPolicyGateway, times(1)).getTotal();
    }

    @Test
    void testExecuteGetWPlate_Success() {
        String licensePlateNumber = "ABC123";
        String tckn = "12345678901";

        carPolicyModel = new CarPolicyModel(
                1L, null, "Test Description", 1, null,
                1000.0, 1L, licensePlateNumber, tckn,
                null, null, null, 1, 1, null, null, null);

        List<CarPolicy> carPolicyEntityList = List.of(carPolicyEntity);
        when(carPolicyGateway.getCarPoliciesByPlateAndTckn(licensePlateNumber, tckn)).thenReturn(carPolicyEntityList);

        List<CarPolicyModel> models = executeCarPolicy.executeGetWPlate(carPolicyModel);

        assertNotNull(models);
        assertFalse(models.isEmpty());
        verify(carPolicyGateway, times(1)).getCarPoliciesByPlateAndTckn(licensePlateNumber, tckn);
    }

    @Test
    void testExecuteGetWPlate_EntityNotFoundException() {
        String licensePlateNumber = "ABC123";
        String tckn = "12345678901";

        carPolicyModel = new CarPolicyModel(
                1L, null, "Test Description", 1, null,
                1000.0, 1L, licensePlateNumber, tckn,
                null, null, null, 1, 1, null, null, null);

        when(carPolicyGateway.getCarPoliciesByPlateAndTckn(licensePlateNumber, tckn)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeGetWPlate(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).getCarPoliciesByPlateAndTckn(licensePlateNumber, tckn);
    }

    @Mock
    private CarPolicyGateway carPolicyGateway;

    @Mock
    private ExecuteCustomer executeCustomer;

    @Mock
    private ExecuteLicensePlate executeLicensePlate;

    @Mock
    private CarPolicyMapper carPolicyMapper;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private LicensePlateMapper licensePlateMapper;

    @Mock
    private CarPolicySpecificationBuild carPolicySpecificationBuild;

    @InjectMocks
    private ExecuteCarPolicy executeCarPolicy;

    private CarPolicyModel carPolicyModel;
    private CarPolicy carPolicyEntity;
    private CustomerModel customerModel;
    private LicensePlateModel licensePlateModel;

    @BeforeEach
    void setUp() {
        carPolicyModel = new CarPolicyModel(
                1L, null, "Test Description", 1, null,
                1000.0, 1L, "ABC123", "12345678901",
                null, null, null, 1, 1, null, null, null);
        customerModel = new CustomerModel(
                1L, "John Doe", "johndoe@example.com", "1234567890", null, null,
                null, 0, null, 0, 0, null);

        licensePlateModel = new LicensePlateModel(
                null, "2023-10-01", null, null, 100, null,null,
                null);

        carPolicyEntity = new CarPolicy();
        carPolicyEntity.setId(carPolicyModel.policyId());
        carPolicyEntity.setPolicyDescription(carPolicyModel.policyDescription());
        carPolicyEntity.setPolicyAmount(carPolicyModel.policyAmount());
    }

    @Test
    void testChangeCarPolicyState_Activate_Success() {
        when(carPolicyGateway.SetStateCarPolicy(any(CarPolicy.class), eq(PolicyState.ACTIVE))).thenReturn(carPolicyEntity);

        CarPolicyModel result = executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE);

        assertNotNull(result);
        assertEquals(carPolicyModel.policyId(), result.policyId());
        verify(carPolicyGateway, times(1)).SetStateCarPolicy(any(CarPolicy.class), eq(PolicyState.ACTIVE));
    }

    @Test
    void testChangeCarPolicyState_Cancel_Success() {
        when(carPolicyGateway.SetStateCarPolicy(any(CarPolicy.class), eq(PolicyState.CANCELLED))).thenReturn(carPolicyEntity);

        CarPolicyModel result = executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.CANCEL);

        assertNotNull(result);
        assertEquals(carPolicyModel.policyId(), result.policyId());
        verify(carPolicyGateway, times(1)).SetStateCarPolicy(any(CarPolicy.class), eq(PolicyState.CANCELLED));
    }

    @Test
    void testChangeCarPolicyState_EntityNotFoundException() {
        when(carPolicyGateway.SetStateCarPolicy(any(CarPolicy.class), any())).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).SetStateCarPolicy(any(CarPolicy.class), eq(PolicyState.ACTIVE));
    }

    @Test
    void testChangeCarPolicyState_IllegalStateException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.EXPIRE));

        verify(carPolicyGateway, times(0)).SetStateCarPolicy(any(CarPolicy.class), any());
    }


    @Test
    void testExecuteCreate_Success() {
        Customer customerEntity = new Customer();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");
        customerEntity.setTckn("12345678901");

        LicensePlate licensePlateEntity = new LicensePlate();
        licensePlateEntity.setPlate("ABC123");

        CarPolicy carPolicyEntity = new CarPolicy();
        carPolicyEntity.setPolicyDescription("Test Description");

        when(executeCustomer.executeGet(any())).thenReturn(CustomerModelFactory.createCustomerModelWithTckn("12345678901"));
        when(executeLicensePlate.ExecuteGetLicensePlate(any())).thenReturn(licensePlateModel);

        when(customerMapper.customerModelToCustomerEntity(any(CustomerModel.class))).thenReturn(customerEntity);
        when(licensePlateMapper.LicensePlateModelToCustomerEntity(any(LicensePlateModel.class))).thenReturn(licensePlateEntity);
        when(carPolicyGateway.create(any(CarPolicy.class), eq(customerEntity), eq(licensePlateEntity)))
                .thenReturn(carPolicyEntity);

        CarPolicyModel createdModel = executeCarPolicy.executeCreate(carPolicyModel);

        assertNotNull(createdModel);
        verify(carPolicyGateway, times(1)).create(any(CarPolicy.class), eq(customerEntity), eq(licensePlateEntity));
    }





    @Test
    void testExecuteUpdate_Success() {
        when(carPolicyGateway.update(any(CarPolicy.class))).thenReturn(carPolicyEntity);

        CarPolicyModel updatedModel = executeCarPolicy.executeUpdate(carPolicyModel);

        assertNotNull(updatedModel);
        assertEquals(carPolicyModel.policyId(), updatedModel.policyId());
        assertEquals(carPolicyModel.policyDescription(), updatedModel.policyDescription());
        assertEquals(carPolicyModel.policyAmount(), updatedModel.policyAmount());

        verify(carPolicyGateway, times(1)).update(any(CarPolicy.class));
    }

    @Test
    void testExecuteUpdate_EntityNotFoundException() {
        when(carPolicyGateway.update(any(CarPolicy.class))).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeUpdate(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).update(any(CarPolicy.class));
    }



    @Test
    void testExecuteCreate_EntityNotFoundException() {
        when(executeCustomer.executeGet(any())).thenThrow(new EntityNotFoundException(1L,"Customer not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeCreate(carPolicyModel));

        assertEquals("Customer not found", exception.getMessage());
        verify(carPolicyGateway, times(0)).create(any(CarPolicy.class), any(Customer.class), any(LicensePlate.class));
    }

    @Test
    void testExecuteGet_Success() {
        when(carPolicyGateway.get(any(CarPolicy.class))).thenReturn(carPolicyEntity);

        CarPolicyModel retrievedModel = executeCarPolicy.executeGet(carPolicyModel);

        assertNotNull(retrievedModel);
        assertEquals(carPolicyModel.policyId(), retrievedModel.policyId());
        assertEquals(carPolicyModel.policyDescription(), retrievedModel.policyDescription());
        assertEquals(carPolicyModel.policyAmount(), retrievedModel.policyAmount());

        verify(carPolicyGateway, times(1)).get(any(CarPolicy.class));
    }

    @Test
    void testExecuteGet_EntityNotFoundException() {
        when(carPolicyGateway.get(any(CarPolicy.class))).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeGet(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).get(any(CarPolicy.class));
    }

    @Test
    void testExecuteDelete_Success() {
        when(carPolicyGateway.delete(any(CarPolicy.class))).thenReturn(carPolicyEntity);

        CarPolicyModel deletedModel = executeCarPolicy.executeDelete(carPolicyModel);

        assertNotNull(deletedModel);
        assertEquals(carPolicyModel.policyId(), deletedModel.policyId());
        assertEquals(carPolicyModel.policyDescription(), deletedModel.policyDescription());
        assertEquals(carPolicyModel.policyAmount(), deletedModel.policyAmount());

        verify(carPolicyGateway, times(1)).delete(any(CarPolicy.class));
    }

    @Test
    void testExecuteDelete_EntityNotFoundException() {
        when(carPolicyGateway.delete(any(CarPolicy.class))).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeDelete(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).delete(any(CarPolicy.class));
    }

    @Test
    void testExecuteGet_wPolicy_Success() {
        String tckn = "12345678901";
        List<CarPolicy> carPolicyEntityList = List.of(carPolicyEntity);

        when(carPolicyGateway.getCarPoliciesByCustomer(tckn)).thenReturn(carPolicyEntityList);

        List<CarPolicyModel> models = executeCarPolicy.executeGet_wPolicy(carPolicyModel);

        assertNotNull(models);
        assertFalse(models.isEmpty());
        verify(carPolicyGateway, times(1)).getCarPoliciesByCustomer(tckn);
    }

    @Test
    void testExecuteGet_BetweenDate_Success() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        List<CarPolicy> carPolicyEntityList = List.of(carPolicyEntity);

        when(carPolicyGateway.getCarPoliciesBetweenDate(startDate, endDate)).thenReturn(carPolicyEntityList);

        carPolicyModel = new CarPolicyModel(
                1L, null, "Test Description", 1, null,
                1000.0, 1L, "ABC123", "12345678901",
                startDate, endDate, null, 1, 1, null, null, null);

        List<CarPolicyModel> models = executeCarPolicy.executeGet_BetweenDate(carPolicyModel);

        assertNotNull(models);
        assertFalse(models.isEmpty());
        verify(carPolicyGateway, times(1)).getCarPoliciesBetweenDate(startDate, endDate);
    }

    @Test
    void testExecuteGet_BetweenDate_EntityNotFoundException() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        when(carPolicyGateway.getCarPoliciesBetweenDate(startDate, endDate)).thenReturn(null);

        carPolicyModel = new CarPolicyModel(
                1L, null, "Test Description", 1, null,
                1000.0, 1L, "ABC123", "12345678901",
                startDate, endDate, null, 1, 1, null, null, null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeGet_BetweenDate(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).getCarPoliciesBetweenDate(startDate, endDate);
    }

    @Test
    void testExecuteGet_wPolicy_EntityNotFoundException() {
        String tckn = "12345678901";

        when(carPolicyGateway.getCarPoliciesByCustomer(tckn)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeGet_wPolicy(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicyGateway, times(1)).getCarPoliciesByCustomer(tckn);
    }

    @Test
    void testExecuteGetList_Success() {
        Specification<CarPolicy> specification = mock(Specification.class);
        List<CarPolicy> carPolicyEntityList = List.of(carPolicyEntity);
        when(carPolicySpecificationBuild.CarPolicyBuild(any(CarPolicy.class), anyString(), anyString())).thenReturn(specification);
        when(carPolicyGateway.getList(eq(specification), anyInt(), anyInt())).thenReturn(carPolicyEntityList);

        List<CarPolicyModel> models = executeCarPolicy.executeGetList(carPolicyModel);

        assertNotNull(models);
        assertFalse(models.isEmpty());
        verify(carPolicySpecificationBuild, times(1)).CarPolicyBuild(any(CarPolicy.class), anyString(), anyString());
        verify(carPolicyGateway, times(1)).getList(eq(specification), anyInt(), anyInt());
    }

    @Test
    void testExecuteGetList_EntityNotFoundException() {
        Specification<CarPolicy> specification = mock(Specification.class);
        when(carPolicySpecificationBuild.CarPolicyBuild(any(CarPolicy.class), anyString(), anyString())).thenReturn(specification);
        when(carPolicyGateway.getList(eq(specification), anyInt(), anyInt())).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> executeCarPolicy.executeGetList(carPolicyModel));

        assertEquals("Entity not found", exception.getMessage());
        verify(carPolicySpecificationBuild, times(1)).CarPolicyBuild(any(CarPolicy.class), anyString(), anyString());
        verify(carPolicyGateway, times(1)).getList(eq(specification), anyInt(), anyInt());
    }
}
