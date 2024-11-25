package PolicyProject.policyService.infrastructure.config.Specifications;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerSpecificationTest {

    @Test
    void testHasTcknWithValidTckn() {
        // Arrange
        String tckn = "12345678901";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("tckn")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(tckn))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasTckn(tckn);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("tckn");
        verify(criteriaBuilder).equal(any(), eq(tckn));
    }


    @Test
    void testHasTcknWithNullTckn() {
        Specification<Customer> specification = CustomerSpecification.hasTckn(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }


    @Test
    void testBuildWithTckn() {
        String tckn = "12345678901";
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("tckn")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(tckn))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasTckn(tckn);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("tckn");
        verify(criteriaBuilder).equal(any(), eq(tckn));
    }

    @Test
    void testBuildWithAddress() {
        String address = "123 Main St";
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("address")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(address))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasAddress(address);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("address");
        verify(criteriaBuilder).equal(any(), eq(address));
    }

    @Test
    void testBuildWithPhone() {
        String phone = "5551234";
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("phone")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(phone))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasPhone(phone);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("phone");
        verify(criteriaBuilder).equal(any(), eq(phone));
    }

    @Test
    void testBuildWithEmail() {
        String email = "test@example.com";
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("email")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(email))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasEmail(email);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("email");
        verify(criteriaBuilder).equal(any(), eq(email));
    }

    @Test
    void testHasBirthDayWithValidDate() {
        // Arrange
        LocalDate birthDay = LocalDate.of(1990, 1, 1);

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("birthDay")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(birthDay))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasBirthDay(birthDay);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("birthDay");
        verify(criteriaBuilder).equal(any(), eq(birthDay));
    }

    @Test
    void testBuildWithGender() {
        Integer gender = 1;

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        when(root.get("gender")).thenReturn(Mockito.mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(gender))).thenReturn(predicate);

        Specification<Customer> specification = CustomerSpecification.build(null, null, null, null, null, null, gender);

        assertNotNull(specification.toPredicate(root, query, criteriaBuilder));

        verify(root).get("gender");
        verify(criteriaBuilder).equal(any(), eq(gender));
    }

    @Test
    void testBuildWithName() {
        String name = "John Doe";

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        when(root.get("name")).thenReturn(Mockito.mock(Path.class));
        when(criteriaBuilder.like(any(), eq(name))).thenReturn(predicate);

        Specification<Customer> specification = CustomerSpecification.build(name, null, null, null, null, null, null);

        assertNotNull(specification.toPredicate(root, query, criteriaBuilder));

        verify(root).get("name");
        verify(criteriaBuilder).like(any(), eq(name));
    }

    @Test
    void testHasBirthDayWithValidBirthDay() {
        LocalDate birthDay = LocalDate.of(1990, 1, 1);
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("birthDay")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(birthDay))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasBirthDay(birthDay);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        verify(root).get("birthDay");
        verify(criteriaBuilder).equal(any(), eq(birthDay));
    }

    @Test
    void testHasBirthDayWithNullBirthDay() {
        Specification<Customer> specification = CustomerSpecification.hasBirthDay(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testHasAddressWithValidAddress() {
        // Arrange
        String address = "123 Main Street";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("address")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(address))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasAddress(address);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("address");
        verify(criteriaBuilder).equal(any(), eq(address));
    }
    @Test
    void testHasAddressWithNullAddress() {
        Specification<Customer> specification = CustomerSpecification.hasAddress(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testBuildWithAllNull() {
        Specification<Customer> specification = CustomerSpecification.build(null, null, null, null, null, null, null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testHasNameWithValidName() {
        // Arrange
        String name = "John";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("name")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(any(), eq(name))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasName(name);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("name");
        verify(criteriaBuilder).like(any(), eq(name));
    }
    @Test
    void testHasGenderWithValidGender() {
        // Arrange
        Integer gender = 1; // Example gender value

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("gender")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(gender))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasGender(gender);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("gender");
        verify(criteriaBuilder).equal(any(), eq(gender));
    }

    @Test
    void testHasGenderWithNullGender() {
        Specification<Customer> specification = CustomerSpecification.hasGender(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testHasEmailWithValidEmail() {
        // Arrange
        String email = "test@example.com";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("email")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(email))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasEmail(email);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("email");
        verify(criteriaBuilder).equal(any(), eq(email));
    }
    @Test
    void testHasEmailWithNullEmail() {
        Specification<Customer> specification = CustomerSpecification.hasEmail(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testHasNameWithNullName() {
        Specification<Customer> specification = CustomerSpecification.hasName(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testHasPhoneWithValidPhone() {
        // Arrange
        String phone = "5551234567";

        // Mocking Root, CriteriaQuery, and CriteriaBuilder
        Root<Customer> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(root.get("phone")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(phone))).thenReturn(predicate);

        // Act
        Specification<Customer> specification = CustomerSpecification.hasPhone(phone);
        var result = specification.toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);

        // Verify interactions
        verify(root).get("phone");
        verify(criteriaBuilder).equal(any(), eq(phone));
    }

    @Test
    void testHasPhoneWithNullPhone() {
        Specification<Customer> specification = CustomerSpecification.hasPhone(null);

        @SuppressWarnings("unchecked")
        Root<Customer> root = Mockito.mock(Root.class);
        CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        assertNull(specification.toPredicate(root, query, criteriaBuilder));
    }



}

