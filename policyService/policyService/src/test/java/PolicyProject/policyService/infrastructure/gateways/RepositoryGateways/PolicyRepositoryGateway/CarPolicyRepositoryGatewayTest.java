package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.EarthquakePolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.CarPolicyRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarPolicyRepositoryGatewayTest {

    @Mock
    private CarPolicyRepository carPolicyRepository;

    @InjectMocks
    private CarPolicyRepositoryGateway carPolicyRepositoryGateway;

    private CarPolicy carPolicy;
    private Customer customer;
    private LicensePlate licensePlate;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);

        licensePlate = new LicensePlate();
        licensePlate.setId(1L);

        carPolicy = new CarPolicy();
        carPolicy.setId(1L);
        carPolicy.setCustomer(customer);
        carPolicy.setLicensePlate(licensePlate);
        carPolicy.setPolicyStartDate(LocalDate.now());
        carPolicy.setPolicyEndDate(LocalDate.now().plusDays(1));
        carPolicy.setState(PolicyState.CREATED);
        Coverage coverage = new Coverage();
        coverage.setId(2L);
        carPolicy.setCoverage(coverage);    }

    @Test
    void testCreate() {
        when(carPolicyRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());
        when(carPolicyRepository.save(any(CarPolicy.class))).thenReturn(carPolicy);

        CarPolicy result = carPolicyRepositoryGateway.create(carPolicy, customer, licensePlate);

        assertNotNull(result);
        assertEquals(carPolicy.getId(), result.getId());
        verify(carPolicyRepository).save(carPolicy);
    }

    @Test
    void testCreateAlreadyExistsException() {
        when(carPolicyRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(any(Customer.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(carPolicy));

        assertThrows(EarthquakePolicyAlreadyExistsException.class, () -> {
            carPolicyRepositoryGateway.create(carPolicy, customer, licensePlate);
        });
    }

    @Test
    void testGet() {
        when(carPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(carPolicy));

        CarPolicy result = carPolicyRepositoryGateway.get(carPolicy);

        assertNotNull(result);
        assertEquals(carPolicy.getId(), result.getId());
    }

    @Test
    void testUpdate() {
        when(carPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(carPolicy));
        when(carPolicyRepository.save(any(CarPolicy.class))).thenReturn(carPolicy);

        CarPolicy result = carPolicyRepositoryGateway.update(carPolicy);

        assertNotNull(result);
        assertEquals(carPolicy.getId(), result.getId());
    }

    @Test
    void testDelete() {
        when(carPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(carPolicy));
        CarPolicy result = carPolicyRepositoryGateway.delete(carPolicy);

        assertNotNull(result);
        verify(carPolicyRepository).delete(carPolicy);
    }

    @Test
    void testGetList() {
        Page<CarPolicy> page = new PageImpl<>(List.of(carPolicy));
        when(carPolicyRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        List<CarPolicy> result = carPolicyRepositoryGateway.getList(null, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByStateAndExpiryDateBefore() {
        when(carPolicyRepository.findByStateAndExpiryDateBefore(any(PolicyState.class), any(LocalDate.class)))
                .thenReturn(List.of(carPolicy));

        List<CarPolicy> result = carPolicyRepositoryGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSetStateCarPolicy() {
        when(carPolicyRepository.findById(any(Long.class))).thenReturn(Optional.of(carPolicy));
        when(carPolicyRepository.save(any(CarPolicy.class))).thenReturn(carPolicy);

        CarPolicy result = carPolicyRepositoryGateway.SetStateCarPolicy(carPolicy, PolicyState.ACTIVE);

        assertNotNull(result);
        assertEquals(PolicyState.ACTIVE, result.getState());
    }

    @Test
    void testGetTotal() {
        when(carPolicyRepository.count(any(Specification.class))).thenReturn(10L);

        int result = carPolicyRepositoryGateway.getTotal();

        assertEquals(10, result);
    }

    @Test
    void testGetCarPoliciesByCustomer() {
        List<CarPolicy> result = carPolicyRepositoryGateway.getCarPoliciesByCustomer("1234567890");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}