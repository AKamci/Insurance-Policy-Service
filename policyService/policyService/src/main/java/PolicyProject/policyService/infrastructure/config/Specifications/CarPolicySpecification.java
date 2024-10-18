package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CarPolicySpecification {


    public static Specification<CarPolicy> build(
            String policyName, String policyDescription, String policyType,
            Boolean policyStatus, LocalDate startDate, LocalDate endDate,
            Double Amount, String plate, String tckn) {

        return Specification
                .where(hasPolicyName(policyName))
                .and(hasPolicyDescription(policyDescription))
                .and(hasPolicyType(policyType))
                .and(hasPolicyStatus(policyStatus))
                .and(hasPolicyStartDate(startDate))
                .and(hasPolicyEndDate(endDate))
                .and(hasPolicyAmount(Amount))
                .and(hasLicensePlateNumber(plate))
                .and(hasCustomerTckn(tckn));
    }

    public static Specification<CarPolicy> hasPolicyName(String policyName) {
        return (root, query, criteriaBuilder) ->
                policyName == null ? null : criteriaBuilder.equal(root.get("policyName"), policyName);
    }

    public static Specification<CarPolicy> hasPolicyDescription(String policyDescription) {
        return (root, query, criteriaBuilder) ->
                policyDescription == null ? null : criteriaBuilder.equal(root.get("policyDescription"), policyDescription);
    }

    public static Specification<CarPolicy> hasPolicyType(String policyType) {
        return (root, query, criteriaBuilder) ->
                policyType == null ? null : criteriaBuilder.equal(root.get("policyType"), policyType);
    }

    public static Specification<CarPolicy> hasPolicyStatus(Boolean policyStatus) {
        return (root, query, criteriaBuilder) ->
                policyStatus == null ? null : criteriaBuilder.equal(root.get("policyStatus"), policyStatus);
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
        return (root, query, criteriaBuilder) ->
                tckn == null ? null : criteriaBuilder.equal(root.get("customer").get("tckn"), tckn);
    }


}
