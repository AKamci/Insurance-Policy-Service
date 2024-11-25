package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.HealthPolicy.PersonalHealthGateway;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteHealthPolicyWeight;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class ExecutePersonalHealthTest {

    @Mock
    private PersonalHealthGateway personalHealthGateway;

    @Mock
    private ExecuteHealthPolicyWeight executeHealthPolicyWeight;

    @Mock
    private ExecuteCustomer executeCustomer;

    @Mock
    private PersonalHealthMapper personalHealthMapper;

    @Mock
    private PersonalHealth personalHealth;

    @Mock
    private PersonalHealthModel personalHealthModel;
    @Mock
    private CustomerModel customerModel;
    @Mock
    private Customer customer;
    @InjectMocks
    private ExecutePersonalHealth executePersonalHealth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteGetPersonalHealth_EntityFound() {
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(1L, "123456789", null, 1, null, 170, 70.0, 24.2, null, false, false, false, false, false, 10000L);

        PersonalHealth returnedEntity = personalHealth;

        // Mock nesneler ile metod çağrısı yapmalısınız
        when(personalHealthGateway.get(any(PersonalHealth.class))).thenReturn(returnedEntity);
        when(personalHealthMapper.getPersonalHealthEntityToPersonalHealthModel(returnedEntity)).thenReturn(personalHealthModel);

        PersonalHealthModel result = executePersonalHealth.ExecuteGetPersonalHealth(personalHealthModel);

        assertNotNull(result);
    }

    @Test
    void testExecuteGetPersonalHealth_EntityNotFound() {
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(1L, "123456789", null, 1, null, 170, 70.0, 24.2, null, false, false, false, false, false, 10000L);
        PersonalHealth personalHealth = new PersonalHealth();

        when(personalHealthGateway.get(any(PersonalHealth.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executePersonalHealth.ExecuteGetPersonalHealth(personalHealthModel));
    }

    @Test
    void testExecuteGetWithCustomer_EntityFound() {
        PersonalHealth returnedEntity = personalHealth;

        when(personalHealthGateway.getWCustomer(any(PersonalHealth.class))).thenReturn(returnedEntity);
        when(personalHealthMapper.getPersonalHealthEntityToPersonalHealthModel(returnedEntity)).thenReturn(personalHealthModel);
        doReturn(personalHealthModel).when(executeHealthPolicyWeight).Get_APersonalHealthModel(any(PersonalHealthModel.class), any(Integer.class));

        PersonalHealthModel result = executePersonalHealth.ExecuteGetWithCustomer(personalHealthModel);

        assertNotNull(result, "The result should not be null");
        assertEquals(personalHealthModel, result, "The models should be equal");
    }

    @Test
    void testExecuteGetWithCustomer_EntityNotFound() {
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(1L, "123456789", null, 1, null, 170, 70.0, 24.2, null, false, false, false, false, false, 10000L);
        PersonalHealth personalHealth = new PersonalHealth();

        when(personalHealthGateway.getWCustomer(any(PersonalHealth.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executePersonalHealth.ExecuteGetWithCustomer(personalHealthModel));
    }

@Test
void testExecuteCreate_Success() {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    PersonalHealthGateway personalHealthGateway = mock(PersonalHealthGateway.class);
    ExecuteCustomer executeCustomer = mock(ExecuteCustomer.class);

    ExecutePersonalHealth executePersonalHealth = new ExecutePersonalHealth(
            personalHealthGateway,
            null,
            executeCustomer
    );

    PersonalHealthModel personalHealthModel = new PersonalHealthModel(null,null,null,null
    ,null,null,null,null,null,false,false,false,false,false, null);
    CustomerModel customerModel = new CustomerModel(null, null, "12345678901", null, null, null, null, 0, 0, 0, 0, null);
    Customer customer = customerMapper.customerModelToCustomerEntity(customerModel);

    when(executeCustomer.executeGet(any(CustomerModel.class))).thenReturn(customerModel);
    when(personalHealthGateway.create(any(PersonalHealth.class), eq(customer))).thenReturn(new PersonalHealth());

    PersonalHealthModel result = executePersonalHealth.ExecuteCreate(personalHealthModel);

    assertNotNull(result);
}
    @Test
    void testExecuteCreate_CallsDependencies() {
        CustomerMapper customerMapper = CustomerMapper.INSTANCE;
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(1L, "123456789", null, 1, null, 170, 70.0, 24.2, null, false, false, false, false, false, 10000L);
        CustomerModel customerModel = new CustomerModel(null, null, personalHealthModel.tckn(), null, null, null, null, 0, 0, 0, 0, null);
        Customer customerEntity = new Customer();
        PersonalHealth personalHealthEntity = new PersonalHealth();

        when(executeCustomer.executeGet(any(CustomerModel.class))).thenReturn(customerModel);
        when(customerMapper.INSTANCE.customerModelToCustomerEntity(customerModel)).thenReturn(customerEntity);
        when(personalHealthGateway.create(any(PersonalHealth.class), any(Customer.class))).thenReturn(personalHealthEntity);
        when(PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity)).thenReturn(personalHealthModel);

        executePersonalHealth.ExecuteCreate(personalHealthModel);

        verify(executeCustomer).executeGet(any(CustomerModel.class));
        verify(customerMapper.INSTANCE).customerModelToCustomerEntity(customerModel);
        verify(personalHealthGateway).create(any(PersonalHealth.class), any(Customer.class));
        verify(PersonalHealthMapper.INSTANCE).getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity);
    }
}
