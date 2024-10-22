package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;

public class CustomerSpecification {

    public static Specification<Customer> build(
            String name, String tckn, String address, String phone,
            String email, LocalDate birthDay, String gender) {

        return Specification
                .where(hasName(name))
                .and(hasTckn(tckn))
                .and(hasAddress(address))
                .and(hasPhone(phone))
                .and(hasEmail(email))
                .and(hasBirthDay(birthDay))
                .and(hasGender(gender));
    }

    public static Specification<Customer> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), name);
    }

    public static Specification<Customer> hasTckn(String tckn) {
        return (root, query, criteriaBuilder) ->
                tckn == null ? null : criteriaBuilder.equal(root.get("tckn"), tckn);
    }

    public static Specification<Customer> hasAddress(String address) {
        return (root, query, criteriaBuilder) ->
                address == null ? null : criteriaBuilder.equal(root.get("address"), address);
    }

    public static Specification<Customer> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                phone == null ? null : criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<Customer> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Customer> hasBirthDay(LocalDate birthDay) {
        return (root, query, criteriaBuilder) ->
                birthDay == null ? null : criteriaBuilder.equal(root.get("birthDay"), birthDay);
    }

    public static Specification<Customer> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                gender == null ? null : criteriaBuilder.equal(root.get("gender"), gender);
    }
}
