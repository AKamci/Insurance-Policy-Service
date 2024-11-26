package PolicyProject.policyService.interfaces.mappers.PolicyMapperTest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.GetListEarthQuakeRequest;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EarthQuakeMapperTest {

    private final EarthQuakeMapper mapper = Mappers.getMapper(EarthQuakeMapper.class);

    @Test
    public void testCreateEarthQuakeRequestToEarthQuakeModel() {
        // Given
        CreateEarthQuakeRequest request = new CreateEarthQuakeRequest(
                LocalDate.now(), // policyOfferDate
                1L,             // houseId
                100,            // coverageCode
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                "12345678901",
                10000L
        );

        // When
        EarthQuakeModel model = mapper.createEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.houseId(), model.houseId());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.policyAmount().doubleValue(), model.policyAmount().doubleValue());
    }
    @Test
    public void testCreateEarthQuakeRequestToEarthQuakeModel_withNullValues() {
        // Given
        CreateEarthQuakeRequest request = new CreateEarthQuakeRequest(
                null, // policyOfferDate
                null, // houseId
                null, // coverageCode
                null,
                null,
                null,
                null
        );

        // When
        EarthQuakeModel model = mapper.createEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertNull(model.policyOfferDate());
        assertNull(model.houseId());
        assertNull(model.coverageCode());
        assertNull(model.policyStartDate());
        assertNull(model.policyEndDate());
        assertNull(model.tckn());
        assertNull(model.policyAmount());
    }
    @Test
    public void testEarthQuakeModelListToGetEarthQuakeResponseList() {
        // Given
        EarthQuakeModel model1 = new EarthQuakeModel(
                1L,                     // policyId
                1L,                     // houseId
                100,                    // coverageCode
                LocalDate.now(),        // policyOfferDate
                "Test description 1",   // policyDescription
                new Coverage(),         // coverage
                5000.0,                 // policyAmount
                1L,                     // customerId
                "12345678901",          // tckn
                LocalDate.now(),        // policyStartDate
                LocalDate.now().plusDays(30), // policyEndDate
                PolicyState.ACTIVE,     // state
                1,                      // page
                10,                     // size
                5,                      // number
                10,                     // apartmentNumber
                "City",                 // city
                "District",             // district
                "Neighborhood",         // neighborhood
                null,                   // CustomerModel
                null                    // house
        );

        EarthQuakeModel model2 = new EarthQuakeModel(
                2L,                     // policyId
                2L,                     // houseId
                200,                    // coverageCode
                LocalDate.now().minusDays(1), // policyOfferDate
                "Test description 2",   // policyDescription
                new Coverage(),         // coverage
                7000.0,                 // policyAmount
                2L,                     // customerId
                "98765432109",          // tckn
                LocalDate.now().minusDays(1),  // policyStartDate
                LocalDate.now().plusDays(29), // policyEndDate
                PolicyState.CREATED,    // state
                2,                      // page
                15,                     // size
                10,                     // number
                20,                     // apartmentNumber
                "Another City",         // city
                "Another District",     // district
                "Another Neighborhood", // neighborhood
                null,                   // CustomerModel
                null                    // house
        );

        List<EarthQuakeModel> modelList = List.of(model1, model2);

        // When
        List<GetEarthQuakeResponse> responseList = mapper.earthQuakeModelListToGetEarthQuakeResponseList(modelList);

        // Then
        assertNotNull(responseList);
        assertEquals(2, responseList.size());

        GetEarthQuakeResponse response1 = responseList.get(0);
        assertEquals(model1.policyId(), response1.policyId());
        assertEquals(model1.policyOfferDate(), response1.policyOfferDate());
        assertEquals(model1.state(), response1.state());
        assertEquals(model1.policyDescription(), response1.policyDescription());
        assertEquals(model1.coverage(), response1.coverage());
        assertEquals(model1.policyAmount(), response1.policyAmount());
        assertEquals(model1.customerId(), response1.customerId());
        assertEquals(model1.policyStartDate(), response1.policyStartDate());
        assertEquals(model1.policyEndDate(), response1.policyEndDate());
        assertEquals(model1.tckn(), response1.tckn());

        GetEarthQuakeResponse response2 = responseList.get(1);
        assertEquals(model2.policyId(), response2.policyId());
        assertEquals(model2.policyOfferDate(), response2.policyOfferDate());
        assertEquals(model2.state(), response2.state());
        assertEquals(model2.policyDescription(), response2.policyDescription());
        assertEquals(model2.coverage(), response2.coverage());
        assertEquals(model2.policyAmount(), response2.policyAmount());
        assertEquals(model2.customerId(), response2.customerId());
        assertEquals(model2.policyStartDate(), response2.policyStartDate());
        assertEquals(model2.policyEndDate(), response2.policyEndDate());
        assertEquals(model2.tckn(), response2.tckn());
    }
    @Test
    public void testEarthQuakeModelToSetStateEarthQuakeResponse() {
        // Given
        EarthQuakeModel model = new EarthQuakeModel(
                1L,            // policyId
                null,          // houseId
                null,          // coverageCode
                null,          // policyOfferDate
                null,          // policyDescription
                null,          // coverage
                null,          // policyAmount
                null,          // customerId
                null,          // tckn
                null,          // policyStartDate
                null,          // policyEndDate
                null,          // state
                0,             // page
                0,             // size
                null,          // number
                null,          // apartmentNumber
                null,          // city
                null,          // district
                null,          // neighborhood
                null,          // CustomerModel
                null           // house
        );

        // When
        SetStateEarthQuakeResponse response = mapper.earthQuakeModelToSetStateEarthQuakeResponse(model);

        // Then
        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    public void testUpdateEarthQuakeRequestToEarthQuakeModel() {
        // Given
        UpdateEarthQuakeRequest request = new UpdateEarthQuakeRequest(
                1L,
                LocalDate.now(),
                "Test description",
                PolicyState.ACTIVE,
                100,
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                5000.0
        );

        // When
        EarthQuakeModel model = mapper.updateEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.state(), model.state());
        assertEquals(request.coverage(), model.coverage().getId().intValue());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.policyAmount(), model.policyAmount());
    }
    @Test
    public void testSetStateEarthQuakeRequestToEarthQuakeModel() {
        // Given
        SetStateEarthQuakeRequest request = new SetStateEarthQuakeRequest(1L);

        // When
        EarthQuakeModel model = mapper.setStateEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    public void testGetEarthQuakeRequestToEarthQuakeModel() {
        // Given
        GetEarthQuakeRequest request = new GetEarthQuakeRequest(1L);

        // When
        EarthQuakeModel model = mapper.getEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    public void testDeleteEarthQuakeRequestToEarthQuakeModel() {
        // Given
        DeleteEarthQuakeRequest request = new DeleteEarthQuakeRequest(1L);

        // When
        EarthQuakeModel model = mapper.deleteEarthQuakeRequestToEarthQuakeModel(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    public void testGetEarthQuakeRequestListToEarthQuakeModel() {
        // Given
        GetListEarthQuakeRequest request = new GetListEarthQuakeRequest(
                10,                   // size
                1,                    // page
                1L,                   // policyId
                LocalDate.now(),      // policyOfferDate
                "Test description",   // policyDescription
                100,                  // coverageCode
                PolicyState.ACTIVE,   // state
                5000.0,               // policyAmount
                1L,                   // customerId
                "12345678901",        // tckn
                LocalDate.now(),      // policyStartDate
                LocalDate.now().plusDays(30), // policyEndDate
                5,                    // number
                10,                   // apartmentNumber
                "City",               // city
                "District",           // district
                "Neighborhood"        // neighborhood
        );

        // When
        EarthQuakeModel model = mapper.getEarthQuakeRequestListToEarthQuakeModelList(request);

        // Then
        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.state(), model.state());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.policyAmount(), model.policyAmount());
        assertEquals(request.customerId(), model.customerId());
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.page(), model.page());
        assertEquals(request.size(), model.size());
        assertEquals(request.number(), model.number());
        assertEquals(request.apartmentNumber(), model.apartmentNumber());
        assertEquals(request.city(), model.city());
        assertEquals(request.district(), model.district());
        assertEquals(request.neighborhood(), model.neighborhood());
    }
    @Test
    public void testEarthQuakeModelToCreateEarthQuakeResponse() {
        // Given
        EarthQuakeModel model = new EarthQuakeModel(
                1L,           // policyId
                1L,           // houseId
                100,          // coverageCode
                LocalDate.now(), // policyOfferDate
                "Test description", // policyDescription
                new Coverage(), // coverage
                5000.0,        // policyAmount
                1L,            // customerId
                "12345678901", // tckn
                LocalDate.now(), // policyStartDate
                LocalDate.now().plusDays(30), // policyEndDate
                PolicyState.ACTIVE, // state
                1,             // page
                10,            // size
                5,             // number
                10,            // apartmentNumber
                "City",        // city
                "District",    // district
                "Neighborhood", // neighborhood
                null,          // CustomerModel
                null           // house
        );

        // When
        CreateEarthQuakeResponse response = mapper.earthQuakeModelToCreateEarthQuakeResponse(model);

        // Then
        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    public void testEarthQuakeModelToUpdateEarthQuakeResponse() {
        // Given
        EarthQuakeModel model = new EarthQuakeModel(
                1L,                     // policyId
                1L,                     // houseId
                100,                    // coverageCode
                LocalDate.now(),        // policyOfferDate
                "Test description",     // policyDescription
                new Coverage(),         // coverage
                5000.0,                 // policyAmount
                1L,                     // customerId
                "12345678901",          // tckn
                LocalDate.now(),        // policyStartDate
                LocalDate.now().plusDays(30), // policyEndDate
                PolicyState.ACTIVE,     // state
                1,                      // page
                10,                     // size
                5,                      // number
                10,                     // apartmentNumber
                "City",                 // city
                "District",             // district
                "Neighborhood",         // neighborhood
                null,                   // CustomerModel
                null                    // house
        );

        // When
        UpdateEarthQuakeResponse response = mapper.earthQuakeModelToUpdateEarthQuakeResponse(model);

        // Then
        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    public void testEarthQuakeModelToGetEarthQuakeResponse() {
        // Given
        EarthQuakeModel model = new EarthQuakeModel(
                1L,                     // policyId
                null,                   // houseId
                100,                    // coverageCode
                LocalDate.now(),        // policyOfferDate
                "Test description",     // policyDescription
                new Coverage(),         // coverage
                10000.0,                // policyAmount
                1L,                     // customerId
                "12345678901",          // tckn
                LocalDate.now(),        // policyStartDate
                LocalDate.now().plusDays(30), // policyEndDate
                PolicyState.ACTIVE,     // state
                0,                      // page
                0,                      // size
                null,                   // number
                null,                   // apartmentNumber
                null,                   // city
                null,                   // district
                null,                   // neighborhood
                null,                   // CustomerModel
                null                    // house
        );

        // When
        GetEarthQuakeResponse response = mapper.earthQuakeModelToGetEarthQuakeResponse(model);

        // Then
        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
        assertEquals(model.policyOfferDate(), response.policyOfferDate());
        assertEquals(model.state(), response.state());
        assertEquals(model.policyDescription(), response.policyDescription());
        assertEquals(model.coverage(), response.coverage());
        assertEquals(model.policyAmount(), response.policyAmount());
        assertEquals(model.customerId(), response.customerId());
        assertEquals(model.policyStartDate(), response.policyStartDate());
        assertEquals(model.policyEndDate(), response.policyEndDate());
        assertEquals(model.tckn(), response.tckn());
    }
    @Test
    public void testEarthQuakeModelToDeleteEarthQuakeResponse() {
        // Given
        EarthQuakeModel model = new EarthQuakeModel(
                1L,            // policyId
                null,          // houseId
                null,          // coverageCode
                null,          // policyOfferDate
                null,          // policyDescription
                null,          // coverage
                null,          // policyAmount
                null,          // customerId
                null,          // tckn
                null,          // policyStartDate
                null,          // policyEndDate
                null,          // state
                0,             // page
                0,             // size
                null,          // number
                null,          // apartmentNumber
                null,          // city
                null,          // district
                null,          // neighborhood
                null,          // CustomerModel
                null           // house
        );

        // When
        DeleteEarthQuakeResponse response = mapper.earthQuakeModelToDeleteEarthQuakeResponse(model);

        // Then
        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }

}