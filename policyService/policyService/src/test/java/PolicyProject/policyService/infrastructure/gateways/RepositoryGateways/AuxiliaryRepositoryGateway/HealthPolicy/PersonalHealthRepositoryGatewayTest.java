package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.AuxiliaryRepositoryGateway.HealthPolicy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
public class PersonalHealthRepositoryGatewayTest {

    @Mock
    private PersonalHealthRepository personalHealthRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private PersonalHealthRepositoryGateway personalHealthRepositoryGateway;

    private PersonalHealth personalHealth;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setTckn("12345678901");

        personalHealth = new PersonalHealth();
        personalHealth.setId(1L);
        personalHealth.setCustomer(customer);
        personalHealth.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetWCustomer() {
        when(customerRepository.findByTckn(any(String.class))).thenReturn(customer);
        when(personalHealthRepository.findTopByCustomerTcknOrderByCreatedAtDesc(any(String.class))).thenReturn(personalHealth);

        PersonalHealth result = personalHealthRepositoryGateway.getWCustomer(personalHealth);

        assertNotNull(result);
        assertEquals(personalHealth.getId(), result.getId());
    }

    @Test
    void testGetWCustomerCustomerNotFound() {
        when(customerRepository.findByTckn(any(String.class))).thenReturn(null);

        PersonalHealth result = personalHealthRepositoryGateway.getWCustomer(personalHealth);

        assertNull(result);
    }

    @Test
    void testGet() {
        when(personalHealthRepository.findById(any(Long.class))).thenReturn(Optional.of(personalHealth));

        PersonalHealth result = personalHealthRepositoryGateway.get(personalHealth);

        assertNotNull(result);
        assertEquals(personalHealth.getId(), result.getId());
    }

    @Test
    void testGetPersonalHealthNull() {
        PersonalHealth result = personalHealthRepositoryGateway.get(null);

        assertNull(result);
    }

    @Test
    void testCreate() {
        when(personalHealthRepository.save(any(PersonalHealth.class))).thenReturn(personalHealth);

        PersonalHealth result = personalHealthRepositoryGateway.create(personalHealth, customer);

        assertNotNull(result);
        assertEquals(personalHealth.getId(), result.getId());
        verify(personalHealthRepository).save(personalHealth);
    }

    @Test
    void testCreatePersonalHealthNull() {
        PersonalHealth result = personalHealthRepositoryGateway.create(null, customer);

        assertNull(result);
    }

    @Test
    void testCreateDataIntegrityViolationException() {
        when(personalHealthRepository.save(any(PersonalHealth.class))).thenThrow(new DataIntegrityViolationException("Beklenmeyen Hata. Oluşturulamadı."));

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            personalHealthRepositoryGateway.create(personalHealth, customer);
        });

        assertEquals("Beklenmeyen Hata. Oluşturulamadı.", exception.getMessage());
    }
}