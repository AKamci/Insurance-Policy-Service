package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthquakePolicy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EarthQuakeSpecification {


    public static Specification<EarthquakePolicy> build(
            Integer number, Integer apartmentNumber, String city,
            String district, String neighborhood,
            String policyDescription, Coverage coverage,
            PolicyState state, LocalDate startDate, LocalDate endDate,
            Double Amount, String tckn) {

        return Specification
                .where(hasPolicyDescription(policyDescription))
                .and(hasPolicyStartDate(startDate))
                .and(hasPolicyEndDate(endDate))
                .and(hasPolicyType(coverage))
                .and(isActiveBetween(startDate, endDate))
                .and(hasPolicyAmount(Amount))
                .and(hasPolicyStatus(state))
                .and(hasCustomerTckn(tckn))
                .and(hasCity(city))
                .and(hasDistrict(district))
                .and(hasNeighborhood(neighborhood))
                .and(hasNumber(number))
                .and(hasApartmentNumber(apartmentNumber));

    }



    public static Specification<EarthquakePolicy> hasCity(String city) {
        return (root, query, criteriaBuilder) -> city == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("house").join("building").join("address").get("city"), city);
    }

    public static Specification<EarthquakePolicy> hasDistrict(String district) {
        return (root, query, criteriaBuilder) -> district == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("house").join("building").join("address").get("district"), district);
    }

    public static Specification<EarthquakePolicy> hasNeighborhood(String neighborhood) {
        return (root, query, criteriaBuilder) -> neighborhood == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("house").join("building").join("address").get("neighborhood"), neighborhood);
    }

    public static Specification<EarthquakePolicy> hasNumber(Integer number) {

        return (root, query, criteriaBuilder) -> number == 0
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("house").get("number"), number);
    }

    public static Specification<EarthquakePolicy> hasApartmentNumber(Integer apartmentNumber) {
        return (root, query, criteriaBuilder) -> apartmentNumber == 0
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("house").join("building").get("apartmentNumber"), apartmentNumber);
    }

    public static Specification<EarthquakePolicy> hasPolicyDescription(String policyDescription) {
        return (root, query, criteriaBuilder) ->
                policyDescription == null ? null : criteriaBuilder.equal(root.get("policyDescription"), policyDescription);
    }

    public static Specification<EarthquakePolicy> hasPolicyType(Coverage policyType) {
        return (root, query, criteriaBuilder) -> {
            if (policyType == null) {
                return criteriaBuilder.conjunction();
            } else {
                policyType.setId(policyType.getId()%100);
                return criteriaBuilder.equal(root.get("coverage"), policyType);
            }
        };
    }

    public static Specification<EarthquakePolicy> hasPolicyStatus(PolicyState state) {
        return (root, query, criteriaBuilder) ->
                state == null ? null : criteriaBuilder.equal(root.get("state"), state);
    }

    public static Specification<EarthquakePolicy> hasPolicyStartDate(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                startDate == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("policyStartDate"), startDate);
    }

    public static Specification<EarthquakePolicy> hasPolicyEndDate(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                endDate == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("policyEndDate"), endDate);
    }

    public static Specification<EarthquakePolicy> hasPolicyAmount(Double amount) {
        return (root, query, criteriaBuilder) ->
                amount == null ? null: criteriaBuilder.equal(root.get("policyAmount"), amount);

    }


    public static Specification<EarthquakePolicy> hasCustomerTckn(String tckn) {
        return (root, query, criteriaBuilder) -> {
            if (tckn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customer").get("tckn"), tckn);
        };
    }

    public static Specification<EarthquakePolicy> isActiveBetween(LocalDate startDate, LocalDate endDate) {
        return (Root<EarthquakePolicy> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }
            Predicate activeStartDate = criteriaBuilder.lessThanOrEqualTo(root.get("policyEndDate"), endDate);
            Predicate activeEndDate = criteriaBuilder.greaterThanOrEqualTo(root.get("policyStartDate"), startDate);
            return criteriaBuilder.and(activeStartDate, activeEndDate);
        };
    }

}
