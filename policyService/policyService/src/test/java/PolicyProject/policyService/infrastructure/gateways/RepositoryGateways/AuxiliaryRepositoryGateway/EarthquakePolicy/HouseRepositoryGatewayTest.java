package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.AuxiliaryRepositoryGateway.EarthquakePolicy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HouseRepositoryGatewayTest {

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseRepositoryGateway houseRepositoryGateway;

    private House house;
    private Building building;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setNeighborhood("Neighborhood");
        address.setDistrict("District");
        address.setCity("City");

        building = new Building();
        building.setAddress(address);
        building.setApartmentNumber(123);

        house = new House();
        house.setId(1L);
        house.setBuilding(building);
        house.setNumber(10);
    }

    @Test
    void testGetWCustomer() {
        when(houseRepository.findHouseByDetails(any(String.class), any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(house);

        House result = houseRepositoryGateway.getWCustomer(house);

        assertNotNull(result);
        assertEquals(house.getId(), result.getId());
    }

    @Test
    void testGetWCustomerHouseOrBuildingNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            houseRepositoryGateway.getWCustomer(null);
        });

        assertEquals("House veya Build alanı null olamaz", exception.getMessage());

        House houseWithoutBuilding = new House();
        houseWithoutBuilding.setId(2L);

        exception = assertThrows(IllegalArgumentException.class, () -> {
            houseRepositoryGateway.getWCustomer(houseWithoutBuilding);
        });

        assertEquals("House veya Build alanı null olamaz", exception.getMessage());
    }

    @Test
    void testGetWCustomerException() {
        when(houseRepository.findHouseByDetails(any(String.class), any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
                .thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            houseRepositoryGateway.getWCustomer(house);
        });

        assertEquals("Error", exception.getMessage());
    }

    @Test
    void testGet() {
        when(houseRepository.findById(any(Long.class))).thenReturn(Optional.of(house));

        House result = houseRepositoryGateway.get(house);

        assertNotNull(result);
        assertEquals(house.getId(), result.getId());
    }

    @Test
    void testGetException() {
        when(houseRepository.findById(any(Long.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            houseRepositoryGateway.get(house);
        });

        assertEquals("Error", exception.getMessage());
    }
}

