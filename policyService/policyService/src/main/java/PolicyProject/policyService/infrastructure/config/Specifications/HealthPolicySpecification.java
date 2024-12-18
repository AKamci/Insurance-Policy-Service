package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HealthPolicySpecification {
    public static Specification<HealthPolicy> build(
            Integer height, Double weight, Double bmi,
            Boolean alcoholConsumption, Boolean smokeConsumption,
            BloodType bloodType, Boolean isPregnant,
            Boolean hasDisability, Boolean hasPreviousSurgeries,
            String tckn, Coverage coverage, PolicyState state) {

        return Specification
                .where(hasHeight(height))
                .and(hasWeight(weight))
                .and(hasBmi(bmi))
                .and(hasAlcoholConsumption(alcoholConsumption))
                .and(hasSmokeConsumption(smokeConsumption))
                .and(hasBloodType(bloodType))
                .and(isPregnant(isPregnant))
                .and(hasDisability(hasDisability))
                .and(hasPreviousSurgeries(hasPreviousSurgeries))
                .and(hasCustomerTckn(tckn))
                .and(hasPolicyCoverage(coverage))
                .and(hasPolicyState(state));
    }

    public static Specification<HealthPolicy> hasHeight(Integer height) {
        return (root, query, criteriaBuilder) -> height == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("height"), height);
    }

    public static Specification<HealthPolicy> hasWeight(Double weight) {
        return (root, query, criteriaBuilder) -> weight == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("weight"), weight);
    }

    public static Specification<HealthPolicy> hasBmi(Double bmi) {
        return (root, query, criteriaBuilder) -> bmi == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("bmi"), bmi);
    }

    public static Specification<HealthPolicy> hasAlcoholConsumption(Boolean alcoholConsumption) {
        return (root, query, criteriaBuilder) -> alcoholConsumption == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("alcoholConsumption"), alcoholConsumption);
    }

    public static Specification<HealthPolicy> hasSmokeConsumption(Boolean smokeConsumption) {
        return (root, query, criteriaBuilder) -> smokeConsumption == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("smokeConsumption"), smokeConsumption);
    }

    public static Specification<HealthPolicy> hasBloodType(BloodType bloodType) {
        return (root, query, criteriaBuilder) -> bloodType == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("bloodType"), bloodType);
    }

    public static Specification<HealthPolicy> isPregnant(Boolean isPregnant) {
        return (root, query, criteriaBuilder) -> isPregnant == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("isPregnant"), isPregnant);
    }

    public static Specification<HealthPolicy> hasDisability(Boolean hasDisability) {
        return (root, query, criteriaBuilder) -> hasDisability == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("hasDisability"), hasDisability);
    }

    public static Specification<HealthPolicy> hasPreviousSurgeries(Boolean hasPreviousSurgeries) {
        return (root, query, criteriaBuilder) -> hasPreviousSurgeries == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("personalHealth").get("hasPreviousSurgeries"), hasPreviousSurgeries);
    }

    public static Specification<HealthPolicy> hasCustomerTckn(String tckn) {
        return (root, query, criteriaBuilder) -> tckn == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("customer").get("tckn"), tckn);
    }

    public static Specification<HealthPolicy> hasPolicyCoverage(Coverage policyType) {
        return (root, query, criteriaBuilder) -> {
            if (policyType == null) {
                return criteriaBuilder.conjunction();
            } else {
                policyType.setId(policyType.getId()%100);
                return criteriaBuilder.equal(root.get("coverage"), policyType);
            }
        };
    }

    public static Specification<HealthPolicy> hasPolicyState(PolicyState state) {
        return (root, query, criteriaBuilder) -> state == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("state"), state);
    }


    }

