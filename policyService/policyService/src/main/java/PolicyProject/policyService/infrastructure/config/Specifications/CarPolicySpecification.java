package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CarPolicySpecification {


    public static Specification<CarPolicy> build(
            String policyDescription, Coverage coverage,
            PolicyState state, LocalDate startDate, LocalDate endDate,
            Double Amount, String plate, String tckn) {

        return Specification
                .where(hasPolicyDescription(policyDescription))
                .and(hasPolicyStartDate(startDate))
                .and(hasPolicyEndDate(endDate))
                .and(hasPolicyType(coverage))
                .and(isActiveBetween(startDate, endDate))
                .and(hasPolicyAmount(Amount))
                .and(hasPolicyStatus(state))
                .and(hasLicensePlateNumber(plate))
                .and(hasCustomerTckn(tckn));
    }


    public static Specification<CarPolicy> hasPolicyDescription(String policyDescription) {
        return (root, query, criteriaBuilder) ->
                policyDescription == null ? null : criteriaBuilder.equal(root.get("policyDescription"), policyDescription);
    }

    public static Specification<CarPolicy> hasPolicyType(Coverage policyType) {
        return (root, query, criteriaBuilder) -> {
            if (policyType == null) {
                return criteriaBuilder.conjunction();
            } else {
                policyType.setId(policyType.getId()%100);
                return criteriaBuilder.equal(root.get("coverage"), policyType);
            }
        };
    }

    public static Specification<CarPolicy> hasPolicyStatus(PolicyState state) {
        return (root, query, criteriaBuilder) ->
                state == null ? null : criteriaBuilder.equal(root.get("state"), state);
    }

    public static Specification<CarPolicy> hasPolicyStartDate(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                startDate == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("policyStartDate"), startDate);
    }

    public static Specification<CarPolicy> hasPolicyEndDate(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                endDate == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("policyEndDate"), endDate);
    }

    public static Specification<CarPolicy> hasPolicyAmount(Double amount) {
        return (root, query, criteriaBuilder) ->
                amount == null ? null: criteriaBuilder.equal(root.get("policyAmount"), amount);

    }

    public static Specification<CarPolicy> hasLicensePlateNumber(String plate) {
        return (root, query, criteriaBuilder) ->
                plate == null ? null : criteriaBuilder.equal(root.get("licensePlate").get("plate"), plate);
    }

    public static Specification<CarPolicy> hasCustomerTckn(String tckn) {
        return (root, query, criteriaBuilder) -> {
            if (tckn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customer").get("tckn"), tckn);
        };
    }

    public static Specification<CarPolicy> isActiveBetween(LocalDate startDate, LocalDate endDate) {
        return (Root<CarPolicy> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }
            Predicate activeStartDate = criteriaBuilder.lessThanOrEqualTo(root.get("policyEndDate"), endDate);
            Predicate activeEndDate = criteriaBuilder.greaterThanOrEqualTo(root.get("policyStartDate"), startDate);
            return criteriaBuilder.and(activeStartDate, activeEndDate);
        };
    }

}
