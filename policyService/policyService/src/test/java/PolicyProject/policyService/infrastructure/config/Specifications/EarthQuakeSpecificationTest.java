package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EarthQuakeSpecificationTest {

    @Mock
    private Root<EarthquakePolicy> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Path<Object> pathMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(root.get(anyString())).thenReturn(pathMock);
        when(pathMock.get(anyString())).thenReturn(pathMock);
    }

    @Test
    void testHasCity_withNullCity_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasCity(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasCity_withValidCity_returnsEqualPredicate() {
        String city = "Ankara";
        Predicate predicate = mock(Predicate.class);
        Join<Object, Object> houseJoin = mock(Join.class);
        Join<Object, Object> buildingJoin = mock(Join.class);
        Join<Object, Object> addressJoin = mock(Join.class);
        Path<Object> cityPath = mock(Path.class);

        when(root.join("house")).thenReturn(houseJoin);
        when(houseJoin.join("building")).thenReturn(buildingJoin);
        when(buildingJoin.join("address")).thenReturn(addressJoin);
        when(addressJoin.get("city")).thenReturn(cityPath);
        when(criteriaBuilder.equal(cityPath, city)).thenReturn(predicate);

        var spec = EarthQuakeSpecification.hasCity(city);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(cityPath, city);
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasPolicyDescription_withNullDescription_returnsNull() {
        var spec = EarthQuakeSpecification.hasPolicyDescription(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNull(result);
    }

    @Test
    void testHasPolicyDescription_withValidDescription_returnsEqualPredicate() {
        String description = "Deprem Sigortası";
        when(criteriaBuilder.equal(any(), eq(description))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyDescription(description);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(any(), eq(description));
        assertNotNull(result);
    }

    @Test
    void testIsActiveBetween_withValidDates_returnsAndPredicate() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        when(criteriaBuilder.lessThanOrEqualTo(any(), eq(endDate))).thenReturn(mock(Predicate.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(startDate))).thenReturn(mock(Predicate.class));
        when(criteriaBuilder.and(any(), any())).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.isActiveBetween(startDate, endDate);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).lessThanOrEqualTo(any(), eq(endDate));
        verify(criteriaBuilder).greaterThanOrEqualTo(any(), eq(startDate));
        verify(criteriaBuilder).and(any(), any());
        assertNotNull(result);
    }

    @Test
    void testHasPolicyAmount_withNullAmount_returnsNull() {
        var spec = EarthQuakeSpecification.hasPolicyAmount(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNull(result);
    }

    @Test
    void testHasPolicyAmount_withValidAmount_returnsEqualPredicate() {
        Double amount = 1000.0;
        when(criteriaBuilder.equal(any(), eq(amount))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyAmount(amount);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(any(), eq(amount));
        assertNotNull(result);
    }

    @Test
    void testHasDistrict_withNullDistrict_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasDistrict(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasDistrict_withValidDistrict_returnsEqualPredicate() {
        String district = "Cankaya";
        Predicate predicate = mock(Predicate.class);
        Join<Object, Object> houseJoin = mock(Join.class);
        Join<Object, Object> buildingJoin = mock(Join.class);
        Join<Object, Object> addressJoin = mock(Join.class);
        Path<Object> districtPath = mock(Path.class);

        when(root.join("house")).thenReturn(houseJoin);
        when(houseJoin.join("building")).thenReturn(buildingJoin);
        when(buildingJoin.join("address")).thenReturn(addressJoin);
        when(addressJoin.get("district")).thenReturn(districtPath);
        when(criteriaBuilder.equal(districtPath, district)).thenReturn(predicate);

        var spec = EarthQuakeSpecification.hasDistrict(district);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(districtPath, district);
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasNeighborhood_withNullNeighborhood_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasNeighborhood(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasNeighborhood_withValidNeighborhood_returnsEqualPredicate() {
        String neighborhood = "Kizilay";
        Predicate predicate = mock(Predicate.class);
        Join<Object, Object> houseJoin = mock(Join.class);
        Join<Object, Object> buildingJoin = mock(Join.class);
        Join<Object, Object> addressJoin = mock(Join.class);
        Path<Object> neighborhoodPath = mock(Path.class);

        when(root.join("house")).thenReturn(houseJoin);
        when(houseJoin.join("building")).thenReturn(buildingJoin);
        when(buildingJoin.join("address")).thenReturn(addressJoin);
        when(addressJoin.get("neighborhood")).thenReturn(neighborhoodPath);
        when(criteriaBuilder.equal(neighborhoodPath, neighborhood)).thenReturn(predicate);

        var spec = EarthQuakeSpecification.hasNeighborhood(neighborhood);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(neighborhoodPath, neighborhood);
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasNumber_withNullNumber_returnsConjunction() {
        Integer number = 0;
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasNumber(number);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasNumber_withValidNumber_returnsEqualPredicate() {
        Integer number = 123;
        Predicate predicate = mock(Predicate.class);
        Join<Object, Object> houseJoin = mock(Join.class);
        Path<Object> numberPath = mock(Path.class);

        when(root.join("house")).thenReturn(houseJoin);
        when(houseJoin.get("number")).thenReturn(numberPath);
        when(criteriaBuilder.equal(numberPath, number)).thenReturn(predicate);

        var spec = EarthQuakeSpecification.hasNumber(number);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(numberPath, number);
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasApartmentNumber_withNullApartmentNumber_returnsConjunction() {
        Integer apartmentNumber = 0;
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasApartmentNumber(apartmentNumber);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasApartmentNumber_withValidApartmentNumber_returnsEqualPredicate() {
        Integer apartmentNumber = 123;
        Predicate predicate = mock(Predicate.class);
        Join<Object, Object> houseJoin = mock(Join.class);
        Join<Object, Object> buildingJoin = mock(Join.class);
        Path<Object> apartmentNumberPath = mock(Path.class);

        when(root.join("house")).thenReturn(houseJoin);
        when(houseJoin.join("building")).thenReturn(buildingJoin);
        when(buildingJoin.get("apartmentNumber")).thenReturn(apartmentNumberPath);
        when(criteriaBuilder.equal(apartmentNumberPath, apartmentNumber)).thenReturn(predicate);

        var spec = EarthQuakeSpecification.hasApartmentNumber(apartmentNumber);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(apartmentNumberPath, apartmentNumber);
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasPolicyType_withNullPolicyType_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyType(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasPolicyType_withValidPolicyType_returnsEqualPredicate() {
        Coverage coverage = mock(Coverage.class);
        when(criteriaBuilder.equal(any(), eq(coverage))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyType(coverage);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(any(), eq(coverage));
        assertNotNull(result);
    }

    @Test
    void testHasPolicyStatus_withNullPolicyStatus_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyStatus(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasPolicyStatus_withValidPolicyStatus_returnsEqualPredicate() {
        PolicyState state = PolicyState.ACTIVE;
        when(criteriaBuilder.equal(any(), eq(state))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyStatus(state);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(any(), eq(state));
        assertNotNull(result);
    }

    @Test
    void testHasCustomerTckn_withNullCustomerTckn_returnsConjunction() {
        when(criteriaBuilder.conjunction()).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasCustomerTckn(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).conjunction();
        assertNotNull(result);
    }

    @Test
    void testHasCustomerTckn_withValidCustomerTckn_returnsEqualPredicate() {
        String tckn = "12345678901";
        when(criteriaBuilder.equal(any(), eq(tckn))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasCustomerTckn(tckn);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).equal(any(), eq(tckn));
        assertNotNull(result);
    }

    @Test
    void testHasPolicyStartDate_withNullStartDate_returnsNull() {
        var spec = EarthQuakeSpecification.hasPolicyStartDate(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNull(result);
    }

    @Test
    void testHasPolicyStartDate_withValidStartDate_returnsGreaterThanOrEqualToPredicate() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(startDate))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyStartDate(startDate);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).greaterThanOrEqualTo(any(), eq(startDate));
        assertNotNull(result);
    }

    @Test
    void testHasPolicyEndDate_withNullEndDate_returnsNull() {
        var spec = EarthQuakeSpecification.hasPolicyEndDate(null);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNull(result);
    }

    @Test
    void testHasPolicyEndDate_withValidEndDate_returnsLessThanOrEqualToPredicate() {
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        when(criteriaBuilder.lessThanOrEqualTo(any(), eq(endDate))).thenReturn(mock(Predicate.class));

        var spec = EarthQuakeSpecification.hasPolicyEndDate(endDate);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).lessThanOrEqualTo(any(), eq(endDate));
        assertNotNull(result);
    }
}
