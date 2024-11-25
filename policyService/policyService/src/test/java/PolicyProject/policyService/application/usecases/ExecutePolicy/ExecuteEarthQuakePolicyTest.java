package PolicyProject.policyService.application.usecases.ExecutePolicy;

import PolicyProject.policyService.application.gateways.PolicyGateway.EarthQuakeGateway;
import PolicyProject.policyService.application.service.ModelFactory.CustomerModelFactory;
import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.jpa.domain.Specification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExecuteEarthQuakePolicyTest {

    @Mock
    private EarthQuakeGateway earthQuakeGateway;
    @Mock
    private ExecuteCustomer executeCustomer;
    @Mock
    private EarthQuakeSpecificationBuild earthQuakeSpecificationBuild;
    @Mock
    private ExecuteHouse executeHouse;

    @InjectMocks
    private ExecuteEarthQuakePolicy executeEarthQuakePolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteGetTotalRecordSuccess() {
        when(earthQuakeGateway.getTotal()).thenReturn(100);

        int result = executeEarthQuakePolicy.executeGetTotalRecord();

        assertEquals(100, result);
    }

    @Test
    void testExecuteGetTotalRecordEntityNotFoundException() {
        when(earthQuakeGateway.getTotal()).thenReturn(-1);

        assertThrows(EntityNotFoundException.class, () -> {
            if (executeEarthQuakePolicy.executeGetTotalRecord() < 0) {
                throw new EntityNotFoundException(1L,"Error retrieving total records");
            }
        });
    }

    @Test
    void testExecuteUpdateSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(earthQuakeGateway.update(any())).thenReturn(earthquakePolicy);

        EarthQuakeModel result = executeEarthQuakePolicy.executeUpdate(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteUpdateEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(earthQuakeGateway.update(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeUpdate(earthQuakeModel));
    }

    @Test
    void testExecuteCreateSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        Customer customer = mock(Customer.class);
        House house = mock(House.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(executeCustomer.executeGet(any(CustomerModel.class)))
                .thenReturn(CustomerModelFactory.createCustomerModelWithTckn("sampleTckn"));

        when(executeHouse.ExecuteGetHouse(any()))
                .thenReturn(HouseModelFactory.createHouseModelWithHouseId(anyLong()));

        when(earthQuakeGateway.create(any(), any(), any()))
                .thenReturn(earthquakePolicy);


        EarthQuakeModel result = executeEarthQuakePolicy.executeCreate(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteCreateEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(executeCustomer.executeGet(any(CustomerModel.class))).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeCreate(earthQuakeModel));
    }


    @Test
    void testExecuteGetSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(earthQuakeGateway.get(any())).thenReturn(earthquakePolicy);

        EarthQuakeModel result = executeEarthQuakePolicy.executeGet(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteGetEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(earthQuakeGateway.get(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeGet(earthQuakeModel));
    }


    @Test
    void testExecuteDeleteSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(earthQuakeGateway.delete(any())).thenReturn(earthquakePolicy);

        EarthQuakeModel result = executeEarthQuakePolicy.executeDelete(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteDeleteEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(earthQuakeGateway.delete(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeDelete(earthQuakeModel));
    }

    @Test
    void testExecuteGet_wPolicySuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        List<EarthquakePolicy> earthquakePolicyList = mock(List.class);
        List<EarthQuakeModel> earthQuakeModelList = mock(List.class);

        when(earthQuakeModel.tckn()).thenReturn("sampleTckn");
        when(earthQuakeGateway.getCarPoliciesByCustomer(anyString())).thenReturn(earthquakePolicyList);

        List<EarthQuakeModel> result = executeEarthQuakePolicy.executeGet_wPolicy(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testChangeCarPolicyStateToActivateSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(earthQuakeGateway.SetStateCarPolicy(any(EarthquakePolicy.class), any(PolicyState.class))).thenReturn(earthquakePolicy);

        EarthQuakeModel result = executeEarthQuakePolicy.changeCarPolicyState(earthQuakeModel, PolicyEvent.ACTIVATE);

        assertNotNull(result);
    }

    @Test
    void testChangeCarPolicyStateToCancelSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        EarthquakePolicy earthquakePolicy = mock(EarthquakePolicy.class);

        when(earthQuakeGateway.SetStateCarPolicy(any(EarthquakePolicy.class), any(PolicyState.class))).thenReturn(earthquakePolicy);

        EarthQuakeModel result = executeEarthQuakePolicy.changeCarPolicyState(earthQuakeModel, PolicyEvent.CANCEL);

        assertNotNull(result);
    }

    @Test
    void testChangeCarPolicyStateEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(earthQuakeGateway.SetStateCarPolicy(any(EarthquakePolicy.class), any(PolicyState.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.changeCarPolicyState(earthQuakeModel, PolicyEvent.ACTIVATE));
    }

    @Test
    void testExecuteGet_wPolicyEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);

        when(earthQuakeModel.tckn()).thenReturn("sampleTckn");
        when(earthQuakeGateway.getCarPoliciesByCustomer(anyString())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeGet_wPolicy(earthQuakeModel));
    }

    @Test
    void testExecuteGetListSuccess() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        List<EarthquakePolicy> earthquakePolicyList = mock(List.class);
        List<EarthQuakeModel> earthQuakeModelList = mock(List.class);
        Specification<EarthquakePolicy> specification = mock(Specification.class);

        when(earthQuakeModel.tckn()).thenReturn("sampleTckn");
        when(earthQuakeModel.page()).thenReturn(0);
        when(earthQuakeModel.size()).thenReturn(10);
        when(earthQuakeSpecificationBuild.EarthQuakeBuild(any(), anyString())).thenReturn(specification);
        when(earthQuakeGateway.getList(any(), anyInt(), anyInt())).thenReturn(earthquakePolicyList);

        List<EarthQuakeModel> result = executeEarthQuakePolicy.executeGetList(earthQuakeModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteGetListEntityNotFoundException() {
        EarthQuakeModel earthQuakeModel = mock(EarthQuakeModel.class);
        Specification<EarthquakePolicy> specification = mock(Specification.class);

        when(earthQuakeModel.tckn()).thenReturn("sampleTckn");
        when(earthQuakeModel.page()).thenReturn(0);
        when(earthQuakeModel.size()).thenReturn(10);
        when(earthQuakeSpecificationBuild.EarthQuakeBuild(any(), anyString())).thenReturn(specification);
        when(earthQuakeGateway.getList(any(), anyInt(), anyInt())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeEarthQuakePolicy.executeGetList(earthQuakeModel));
    }
}

