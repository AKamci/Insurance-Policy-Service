package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.AuxiliaryRepositoryGateway.CarPolicy;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.LicensePlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LicensePlateRepositoryGatewayTest {

    @Mock
    private LicensePlateRepository licensePlateRepository;

    @InjectMocks
    private LicensePlateRepositoryGateway licensePlateRepositoryGateway;

    private LicensePlate licensePlate;

    @BeforeEach
    void setUp() {
        licensePlate = new LicensePlate();
        licensePlate.setPlate("34ABC34");
    }

    @Test
    void testGetWCustomer() {
        when(licensePlateRepository.findByPlate(anyString())).thenReturn(licensePlate);

        LicensePlate result = licensePlateRepositoryGateway.getWCustomer(licensePlate);

        assertNotNull(result);
        assertEquals(licensePlate.getPlate(), result.getPlate());
    }

    @Test
    void testGetWCustomerLicensePlateNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            licensePlateRepositoryGateway.getWCustomer(null);
        });

        assertEquals("LicensePlate veya plate alanı null olamaz", exception.getMessage());

        LicensePlate licensePlateWithoutPlate = new LicensePlate();

        exception = assertThrows(IllegalArgumentException.class, () -> {
            licensePlateRepositoryGateway.getWCustomer(licensePlateWithoutPlate);
        });

        assertEquals("LicensePlate veya plate alanı null olamaz", exception.getMessage());
    }

    @Test
    void testGetWCustomerException() {
        when(licensePlateRepository.findByPlate(anyString())).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            licensePlateRepositoryGateway.getWCustomer(licensePlate);
        });

        assertEquals("Error", exception.getMessage());
    }

    @Test
    void testGet() {
        when(licensePlateRepository.findByPlate(anyString())).thenReturn(licensePlate);

        LicensePlate result = licensePlateRepositoryGateway.get(licensePlate.getPlate());

        assertNotNull(result);
        assertEquals(licensePlate.getPlate(), result.getPlate());
    }

    @Test
    void testGetException() {
        when(licensePlateRepository.findByPlate(anyString())).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            licensePlateRepositoryGateway.get(licensePlate.getPlate());
        });

        assertEquals("Error", exception.getMessage());
    }
}