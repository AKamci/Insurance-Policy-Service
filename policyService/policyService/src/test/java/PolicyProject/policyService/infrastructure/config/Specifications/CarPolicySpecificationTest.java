package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CarPolicySpecificationTest {

    @Test
    public void testBuildSpecification() {
        Specification<CarPolicy> spec = CarPolicySpecification.build(
                "Policy Description", new Coverage(), PolicyState.ACTIVE,
                LocalDate.now(), LocalDate.now().plusDays(10), 1000.0,
                "34ABC34", "12345678901");

        assertNotNull(spec);
    }

    @Test
    public void testHasPolicyDescription() {
        String policyDescription = "Test";

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("policyDescription")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(policyDescription))).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyDescription(policyDescription);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testHasPolicyType() {
        Coverage policyType = new Coverage();
        policyType.setId(102L); // input id

        Long adjustedId = 102L % 100; // adjusted id

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        Path<Coverage> coveragePath = mock(Path.class);

        // Mock behavior
        when(root.get("coverage")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(coveragePath, policyType)).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyType(policyType);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("coverage");
        verify(criteriaBuilder).equal(coveragePath, policyType);
    }
    @Test
    public void testHasPolicyStatus() {
        PolicyState state = PolicyState.ACTIVE;

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("state")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(state))).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyStatus(state);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testIsActiveBetween() {
        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(criteriaBuilder.lessThanOrEqualTo(any(Expression.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.greaterThanOrEqualTo(any(Expression.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(), any())).thenReturn(predicate);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(10);

        Specification<CarPolicy> spec = CarPolicySpecification.isActiveBetween(startDate, endDate);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).lessThanOrEqualTo(any(), eq(endDate));
        verify(criteriaBuilder).greaterThanOrEqualTo(any(), eq(startDate));
        verify(criteriaBuilder).and(any(), any());
    }

    @Test
    public void testHasPolicyStartDate() {
        LocalDate startDate = LocalDate.of(2023, 11, 25);

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("policyStartDate")).thenReturn(mock(Path.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(startDate))).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyStartDate(startDate);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testHasPolicyEndDate() {
        LocalDate endDate = LocalDate.of(2023, 11, 25);

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("policyEndDate")).thenReturn(mock(Path.class));
        when(criteriaBuilder.lessThanOrEqualTo(any(), eq(endDate))).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyEndDate(endDate);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testHasPolicyAmount() {
        double policyAmount = 3050.0;

        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("policyAmount")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(policyAmount))).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasPolicyAmount(policyAmount);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testHasLicensePlateNumber() {
        String plate = "34ABC0010";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        Path<Object> licensePath = mock(Path.class);
        Path<Object> platePath = mock(Path.class);

        when(root.get("licensePlate")).thenReturn(licensePath);
        when(licensePath.get("plate")).thenReturn(platePath);
        when(criteriaBuilder.equal(platePath, plate)).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasLicensePlateNumber(plate);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    public void testHasCustomerTckn() {
        String tckn = "12345678901";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<CarPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        Path<Object> customerPath = mock(Path.class);
        Path<Object> tcknPath = mock(Path.class);

        when(root.get("customer")).thenReturn(customerPath);
        when(customerPath.get("tckn")).thenReturn(tcknPath);
        when(criteriaBuilder.equal(tcknPath, tckn)).thenReturn(predicate);

        // Act
        Specification<CarPolicy> specification = CarPolicySpecification.hasCustomerTckn(tckn);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
    }
}