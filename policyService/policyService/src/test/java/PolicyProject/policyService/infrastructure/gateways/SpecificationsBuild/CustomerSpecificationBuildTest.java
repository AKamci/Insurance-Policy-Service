package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.CustomerSpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerSpecificationBuildTest {

    @InjectMocks
    private CustomerSpecificationBuild customerSpecificationBuild;

    @Mock
    private CustomerSpecification customerSpecification;

    @Test
    void testCustomerBuildWithValidCustomer() {
        try (MockedStatic<CustomerSpecification> mockedStatic = mockStatic(CustomerSpecification.class)) {
            Customer customer = Customer.builder()
                    .id(1L)
                    .name("John Doe")
                    .tckn("12345678901")
                    .address("123 Street")
                    .phone("555-555-5555")
                    .email("test@example.com")
                    .birthDay(LocalDate.of(1990, 1, 1))
                    .gender(1)
                    .build();

            Specification<Customer> mockSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            mockedStatic.when(() -> CustomerSpecification.build(
                    customer.getName(),
                    customer.getTckn(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getBirthDay(),
                    customer.getGender()
            )).thenReturn(mockSpecification);

            Specification<Customer> result = customerSpecificationBuild.CustomerBuild(customer);

            assertNotNull(result, "The result specification should not be null");
        }
    }
}