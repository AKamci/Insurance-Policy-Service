package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.EarthquakePolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway.EarthQuakeRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class EarthQuakeRepositoryGatewayTest {

    @Mock
    private EarthQuakeRepository earthQuakeRepository;

    @Mock
    private PoliciesRepository policiesRepository;

    @InjectMocks
    private EarthQuakeRepositoryGateway earthQuakeRepositoryGateway;

    private EarthquakePolicy earthquakePolicy;
    private Customer customer;
    private House house;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);

        house = new House();
        house.setId(1L);

        earthquakePolicy = new EarthquakePolicy();
        earthquakePolicy.setId(1L);
        earthquakePolicy.setCustomer(customer);
        earthquakePolicy.setHouse(house);
        earthquakePolicy.setPolicyStartDate(LocalDate.now());
        earthquakePolicy.setPolicyEndDate(LocalDate.now().plusDays(1));
        earthquakePolicy.setState(PolicyState.CREATED);
        Coverage coverage = new Coverage();
        coverage.setId(5L);
        earthquakePolicy.setCoverage(coverage);
    }

    @Test
    void testCreate() {
        when(earthQuakeRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());
        when(earthQuakeRepository.save(any(EarthquakePolicy.class))).thenReturn(earthquakePolicy);

        EarthquakePolicy result = earthQuakeRepositoryGateway.create(earthquakePolicy, customer, house);

        assertNotNull(result);
        assertEquals(earthquakePolicy.getId(), result.getId());
        verify(earthQuakeRepository).save(earthquakePolicy);
    }

    @Test
    void testCreateAlreadyExistsException() {
        when(earthQuakeRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(earthquakePolicy));

        assertThrows(EarthquakePolicyAlreadyExistsException.class, () -> {
            earthQuakeRepositoryGateway.create(earthquakePolicy, customer, house);
        });
    }

    @Test
    void testGet() {
        when(earthQuakeRepository.findById(any(Long.class))).thenReturn(Optional.of(earthquakePolicy));

        EarthquakePolicy result = earthQuakeRepositoryGateway.get(earthquakePolicy);

        assertNotNull(result);
        assertEquals(earthquakePolicy.getId(), result.getId());
    }

    @Test
    void testUpdate() {
        when(earthQuakeRepository.findById(any(Long.class))).thenReturn(Optional.of(earthquakePolicy));
        when(earthQuakeRepository.save(any(EarthquakePolicy.class))).thenReturn(earthquakePolicy);

        EarthquakePolicy result = earthQuakeRepositoryGateway.update(earthquakePolicy);

        assertNotNull(result);
        assertEquals(earthquakePolicy.getId(), result.getId());
    }

    @Test
    @Transactional
    void testDelete() {
        when(earthQuakeRepository.findById(any(Long.class))).thenReturn(Optional.of(earthquakePolicy));

        EarthquakePolicy result = earthQuakeRepositoryGateway.delete(earthquakePolicy);

        assertNotNull(result);
        verify(earthQuakeRepository).deleteById(earthquakePolicy.getId());
    }

    @Test
    void testGetList() {
        Page<EarthquakePolicy> page = new PageImpl<>(List.of(earthquakePolicy));
        when(earthQuakeRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        List<EarthquakePolicy> result = earthQuakeRepositoryGateway.getList(null, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByStateAndExpiryDateBefore() {
        when(earthQuakeRepository.findByStateAndExpiryDateBefore(any(PolicyState.class), any(LocalDate.class)))
                .thenReturn(List.of(earthquakePolicy));

        List<EarthquakePolicy> result = earthQuakeRepositoryGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSetStateCarPolicy() {
        when(earthQuakeRepository.findById(any(Long.class))).thenReturn(Optional.of(earthquakePolicy));
        when(earthQuakeRepository.save(any(EarthquakePolicy.class))).thenReturn(earthquakePolicy);

        EarthquakePolicy result = earthQuakeRepositoryGateway.SetStateCarPolicy(earthquakePolicy, PolicyState.ACTIVE);

        assertNotNull(result);
        assertEquals(PolicyState.ACTIVE, result.getState());
    }

    @Test
    void testGetTotal() {
        when(earthQuakeRepository.count(any(Specification.class))).thenReturn(10L);

        int result = earthQuakeRepositoryGateway.getTotal();

        assertEquals(10, result);
    }

    @Test
    void testGetCarPoliciesByCustomer() {
        List<EarthquakePolicy> result = earthQuakeRepositoryGateway.getCarPoliciesByCustomer("1234567890");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}