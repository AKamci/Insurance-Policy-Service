package PolicyProject.policyService.application.usecases.ExecutePolicy;

import PolicyProject.policyService.application.gateways.PolicyGateway.HealthPolicyGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.application.service.ModelFactory.CustomerModelFactory;
import PolicyProject.policyService.application.service.ModelFactory.PersonalHealthModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy.ExecutePersonalHealth;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.PolicyModel.HealthPolicyModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.HealthPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.interfaces.mappers.*;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class ExecuteHealthPolicyTest {

    @Mock
    private HealthPolicyGateway healthPolicyGateway;
    @Mock
    private HealthPolicyMapper healthPolicyMapper;
    @Mock
    private ExecuteCustomer executeCustomer;
    @Mock
    private ExecutePersonalHealth executePersonalHealth;
    @Mock
    private HealthPolicySpecificationBuild healthPolicySpecificationBuild;
    @InjectMocks
    private ExecuteHealthPolicy executeHealthPolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldChangeHealthPolicyStateToCancelledSuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyGateway.SetStateCarPolicy(healthPolicyEntity, PolicyState.CANCELLED)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.CANCEL);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).SetStateCarPolicy(healthPolicyEntity, PolicyState.CANCELLED);
    }

    @Test
    public void shouldChangeHealthPolicyStateToActiveSuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyMapper.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)).thenReturn(healthPolicyEntity);
        when(healthPolicyGateway.SetStateCarPolicy(healthPolicyEntity, PolicyState.ACTIVE)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.ACTIVATE);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).SetStateCarPolicy(healthPolicyEntity, PolicyState.ACTIVE);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenChangeHealthPolicyStateToCancelledFails() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyGateway.SetStateCarPolicy(healthPolicyEntity, PolicyState.CANCELLED)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.CANCEL);
        });
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenChangeHealthPolicyStateToActiveFails() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyGateway.SetStateCarPolicy(healthPolicyEntity, PolicyState.ACTIVE)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.ACTIVATE);
        });
    }

    @Test
    public void shouldThrowExceptionForInvalidPolicyEvent() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);

        assertThrows(IllegalStateException.class, () -> {
            executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.EXPIRE);
        });
    }

    @Test
    public void shouldGetListOfHealthPoliciesSuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);
        List<HealthPolicy> healthPolicyEntitiesList = List.of(healthPolicyEntity);

        when(healthPolicySpecificationBuild.HealthPolicyBuild(any(), anyString())).thenReturn(mock(Specification.class));
        when(healthPolicyGateway.getList(any(), anyInt(), anyInt())).thenReturn(healthPolicyEntitiesList);

        List<HealthPolicyModel> results = executeHealthPolicy.executeGetList(healthPolicyModel);

        assertThat(results).containsExactly(healthPolicyModel);
        verify(healthPolicyGateway).getList(any(), anyInt(), anyInt());
    }

    @Test
    public void shouldReturnTotalRecordSuccessfully() {
        when(healthPolicyGateway.getTotal()).thenReturn(100);

        int totalRecords = executeHealthPolicy.executeGetTotalRecord();

        assertThat(totalRecords).isEqualTo(100);
        verify(healthPolicyGateway).getTotal();
    }

    @Test
    public void shouldThrowExceptionWhenTotalRecordFails() {
        when(healthPolicyGateway.getTotal()).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            executeHealthPolicy.executeGetTotalRecord();
        });
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenHealthPoliciesNotFound() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);

        when(healthPolicySpecificationBuild.HealthPolicyBuild(any(), anyString())).thenReturn(mock(Specification.class));
        when(healthPolicyGateway.getList(any(), anyInt(), anyInt())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.executeGetList(healthPolicyModel);
        });
    }

    @Test
    public void shouldUpdateHealthPolicySuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyMapper.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)).thenReturn(healthPolicyEntity);
        when(healthPolicyGateway.update(healthPolicyEntity)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.executeUpdate(healthPolicyModel);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).update(healthPolicyEntity);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenHealthPolicyIsNotFound() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyGateway.update(healthPolicyEntity)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.executeUpdate(healthPolicyModel);
        });
    }

    @Test
    public void shouldCreateHealthPolicySuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        Customer customer = mock(Customer.class);
        PersonalHealth personalHealth = mock(PersonalHealth.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(executeCustomer.executeGet(any())).thenReturn(mock(CustomerModel.class));
        when(executePersonalHealth.ExecuteGetPersonalHealth(any())).thenReturn(mock(PersonalHealthModel.class));
        when(healthPolicyGateway.create(healthPolicyEntity, customer, personalHealth)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.executeCreate(healthPolicyModel);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).create(healthPolicyEntity, customer, personalHealth);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCustomerNotFound() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);

        when(executeCustomer.executeGet(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.executeGet(healthPolicyModel);
        });
    }


    @Test
    public void shouldGetHealthPolicySuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyMapper.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)).thenReturn(healthPolicyEntity);
        when(healthPolicyGateway.get(healthPolicyEntity)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.executeGet(healthPolicyModel);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).get(healthPolicyEntity);
    }

    @Test
    public void shouldDeleteHealthPolicySuccessfully() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyMapper.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)).thenReturn(healthPolicyEntity);
        when(healthPolicyGateway.delete(healthPolicyEntity)).thenReturn(healthPolicyEntity);

        HealthPolicyModel result = executeHealthPolicy.executeDelete(healthPolicyModel);

        assertThat(result).isEqualTo(healthPolicyModel);
        verify(healthPolicyGateway).delete(healthPolicyEntity);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenHealthPolicyToBeDeletedIsNotFound() {
        HealthPolicyModel healthPolicyModel = mock(HealthPolicyModel.class);
        HealthPolicy healthPolicyEntity = mock(HealthPolicy.class);

        when(healthPolicyGateway.delete(healthPolicyEntity)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeHealthPolicy.executeDelete(healthPolicyModel);
        });
    }
}