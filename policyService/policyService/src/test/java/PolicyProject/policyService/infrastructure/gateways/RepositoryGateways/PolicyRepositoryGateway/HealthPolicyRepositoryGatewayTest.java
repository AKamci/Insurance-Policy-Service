package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.HealthPolicyAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HealthPolicyRepositoryGatewayTest {

    @Mock
    private HealthPolicyRepository healthPolicyRepository;

    @Mock
    private PoliciesRepository policiesRepository;

    @InjectMocks
    private HealthPolicyRepositoryGateway healthPolicyRepositoryGateway;

    private HealthPolicy healthPolicy;
    private Customer customer;
    private PersonalHealth personalHealth;

    @BeforeEach
    void setUp() {
        // Arrange
        customer = new Customer();
        customer.setId(1L);

        personalHealth = new PersonalHealth();
        personalHealth.setId(1L);

        healthPolicy = new HealthPolicy();
        healthPolicy.setId(1L);
        healthPolicy.setCustomer(customer);
        healthPolicy.setPersonalHealth(personalHealth);
        healthPolicy.setPolicyStartDate(LocalDate.now());
        healthPolicy.setPolicyEndDate(LocalDate.now().plusDays(1));
        healthPolicy.setState(PolicyState.CREATED);
        Coverage coverage = new Coverage();
        coverage.setId(5L);
        healthPolicy.setCoverage(coverage);
    }

    @Test
    void testCreate() {
        // Arrange
        when(healthPolicyRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());
        when(healthPolicyRepository.save(any(HealthPolicy.class))).thenReturn(healthPolicy);

        // Act
        HealthPolicy result = healthPolicyRepositoryGateway.create(healthPolicy, customer, personalHealth);

        // Assert
        assertNotNull(result);
        assertEquals(healthPolicy.getId(), result.getId());
        verify(healthPolicyRepository).save(healthPolicy);
    }

    @Test
    void testCreateAlreadyExistsException() {
        // Arrange
        when(healthPolicyRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(healthPolicy));

        // Act & Assert
        assertThrows(HealthPolicyAlreadyExistsException.class, () -> {
            healthPolicyRepositoryGateway.create(healthPolicy, customer, personalHealth);
        });
    }

    @Test
    void testGet() {
        // Arrange
        when(healthPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(healthPolicy));

        // Act
        HealthPolicy result = healthPolicyRepositoryGateway.get(healthPolicy);

        // Assert
        assertNotNull(result);
        assertEquals(healthPolicy.getId(), result.getId());
    }

    @Test
    void testUpdate() {
        // Arrange
        when(healthPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(healthPolicy));
        when(healthPolicyRepository.save(any(HealthPolicy.class))).thenReturn(healthPolicy);

        // Act
        HealthPolicy result = healthPolicyRepositoryGateway.update(healthPolicy);

        // Assert
        assertNotNull(result);
        assertEquals(healthPolicy.getId(), result.getId());
    }

    @Test
    void testDelete() {
        // Arrange
        when(healthPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(healthPolicy));

        // Act
        HealthPolicy result = healthPolicyRepositoryGateway.delete(healthPolicy);

        // Assert
        assertNotNull(result);
        verify(healthPolicyRepository).delete(healthPolicy);
        verify(policiesRepository).delete(any(Policies.class));
    }

    @Test
    void testGetList() {
        // Arrange
        Page<HealthPolicy> page = new PageImpl<>(List.of(healthPolicy));
//        when(healthPolicyRepository.findAll(any(Specification.class), any(PageRequest.class)))
//                .thenReturn(page);

        doReturn(page).when(healthPolicyRepository).findAll(any(Specification.class), any(PageRequest.class));

        // Act
        List<HealthPolicy> result = healthPolicyRepositoryGateway.getList(null, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByStateAndExpiryDateBefore() {
        // Arrange
        when(healthPolicyRepository.findByStateAndExpiryDateBefore(any(PolicyState.class), any(LocalDate.class)))
                .thenReturn(List.of(healthPolicy));

        // Act
        List<HealthPolicy> result = healthPolicyRepositoryGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSetStateCarPolicy() {
        // Arrange
        when(healthPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(healthPolicy));
        when(healthPolicyRepository.save(any(HealthPolicy.class))).thenReturn(healthPolicy);

        // Act
        HealthPolicy result = healthPolicyRepositoryGateway.SetStateCarPolicy(healthPolicy, PolicyState.ACTIVE);

        // Assert
        assertNotNull(result);
        assertEquals(PolicyState.ACTIVE, result.getState());
    }

    @Test
    void testGetTotal() {
        // Arrange
        when(healthPolicyRepository.count(any(Specification.class))).thenReturn(10L);

        // Act
        when(healthPolicyRepository.count(any(Specification.class))).thenReturn(10L);

        int result = healthPolicyRepositoryGateway.getTotal();

        // Assert
        assertEquals(10, result);
        verify(healthPolicyRepository).count(any(Specification.class));
    }

    @Test
    void testGetCarPoliciesByCustomer() {
        // Act
        List<HealthPolicy> result = healthPolicyRepositoryGateway.getCarPoliciesByCustomer("1234567890");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}