package PolicyProject.policyService.infrastructure.config.Specifications;



import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class HealthPolicySpecificationTest {

    @Test
    void testBuildSpecification() {
        Specification<HealthPolicy> spec = HealthPolicySpecification.build(
                180, 75.0, 23.1, true, false,
                BloodType.O_POSITIVE, false, false, false,
                "12345678901", new Coverage(), PolicyState.ACTIVE);

        assertNotNull(spec);
    }

    @Test
    void testHasHeight() {
        Integer height = 180;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("height")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(height))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasHeight(height);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasWeight() {
        Double weight = 75.0;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("weight")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(weight))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasWeight(weight);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasBmi() {
        Double bmi = 23.1;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("bmi")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(bmi))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasBmi(bmi);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasAlcoholConsumption() {
        Boolean alcoholConsumption = true;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("alcoholConsumption")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(alcoholConsumption))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasAlcoholConsumption(alcoholConsumption);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasSmokeConsumption() {
        Boolean smokeConsumption = false;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("smokeConsumption")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(smokeConsumption))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasSmokeConsumption(smokeConsumption);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasBloodType() {
        BloodType bloodType = BloodType.A_POSITIVE;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("bloodType")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(bloodType))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasBloodType(bloodType);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testIsPregnant() {
        Boolean isPregnant = false;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("isPregnant")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(isPregnant))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.isPregnant(isPregnant);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasDisability() {
        Boolean hasDisability = true;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("hasDisability")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(hasDisability))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasDisability(hasDisability);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasPreviousSurgeries() {
        Boolean hasPreviousSurgeries = false;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> join = mock(Join.class);
        Predicate predicate = mock(Predicate.class);

        when(root.join("personalHealth")).thenReturn(join);
        when(join.get("hasPreviousSurgeries")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(hasPreviousSurgeries))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasPreviousSurgeries(hasPreviousSurgeries);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasCustomerTckn() {
        String tckn = "12345678901";

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("customer")).thenReturn(mock(Path.class));
        when(root.get("customer").get("tckn")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(tckn))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasCustomerTckn(tckn);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasPolicyCoverage() {
        Coverage policyType = new Coverage();
        policyType.setId(102L);

        Long adjustedId = 102L % 100;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        Path<Long> path = mock(Path.class);

        when(root.get("coverage")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(Path.class), eq(policyType))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasPolicyCoverage(policyType);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }

    @Test
    void testHasPolicyState() {
        PolicyState state = PolicyState.ACTIVE;

        Root<HealthPolicy> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("state")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(state))).thenReturn(predicate);

        Specification<HealthPolicy> spec = HealthPolicySpecification.hasPolicyState(state);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        assertEquals(predicate, result);
    }
}
