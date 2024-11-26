package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapperTest.EarthquakePolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseMapperTest {

    private HouseMapper houseMapper;

    @BeforeEach
    public void setUp() throws Exception {
        houseMapper = HouseMapper.INSTANCE;
    }

    @Test
    public void testGetHouseWithCustomerRequestToHouseModel() {
        GetHouseWCustomerRequest request = new GetHouseWCustomerRequest(
                123,
                456,
                "Springfield",
                "Downtown",
                "Evergreen",
                789,
                "12345678901"
        );

        HouseModel houseModel = houseMapper.getHouseWithCustomerRequestToHouseModel(request);

        assertEquals(request.apartmentNumber(), houseModel.building().getApartmentNumber());
        assertEquals(request.city(), houseModel.building().getAddress().getCity());
        assertEquals(request.district(), houseModel.building().getAddress().getDistrict());
        assertEquals(request.neighborhood(), houseModel.building().getAddress().getNeighborhood());
        assertEquals(request.coverageCode(), houseModel.coverageCode());
    }

    @Test
    public void testHouseModelToGetHouseWCustomerResponse() {
        HouseModel houseModel = new HouseModel(
                1L,
                789,
                123,
                100,
                new Customer(/* provide necessary customer data */),
                Building.builder().apartmentNumber(456).address(Address.builder().city("Springfield").district("Downtown").neighborhood("Evergreen").build()).build(),
                "12345678901",
                20000L
        );

        GetHouseWCustomerResponse response = houseMapper.HouseModelToGetHouseWCustomerResponse(houseModel);

        assertEquals(houseModel.id(), response.id());
        assertEquals(houseModel.number(), response.number());
        assertEquals(houseModel.squareMeters(), response.squareMeters());
        assertEquals(houseModel.customer(), response.customer());
        assertEquals(houseModel.building(), response.building());
        assertEquals(houseModel.tckn(), response.tckn());
        assertEquals(houseModel.Amount(), response.Amount());
    }

    @Test
    public void testHouseModelToHouseEntity() {
        HouseModel houseModel = new HouseModel(
                1L,
                789,
                123,
                100,
                new Customer(/* provide necessary customer data */),
                Building.builder().apartmentNumber(456).address(Address.builder().city("Springfield").district("Downtown").neighborhood("Evergreen").build()).build(),
                "12345678901",
                20000L
        );

        House houseEntity = houseMapper.HouseModelToHouseEntity(houseModel);

        assertEquals(houseModel.id(), houseEntity.getId());
        assertEquals(houseModel.customer(), houseEntity.getCustomer());
        assertEquals(houseModel.building(), houseEntity.getBuilding());
    }
}
