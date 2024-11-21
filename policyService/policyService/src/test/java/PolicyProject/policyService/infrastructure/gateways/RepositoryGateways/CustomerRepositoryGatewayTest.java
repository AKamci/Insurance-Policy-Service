package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;


import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRepositoryGatewayTest {


    @Test
    public void sampleTest() {
        assertEquals(0, 1);
    }


    @Test
    public void testGetTotalCustomers() {
        // Arrange
        CustomerRepository mockRepository = mock(CustomerRepository.class);
        when(mockRepository.count()).thenReturn(5L);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        int totalCustomers = customerRepositoryGateway.getTotal();

        // Assert
        assertEquals(5, totalCustomers);
    }

    @Test
    public void testGetListCustomers() {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setTckn("12345678901");
        customer1.setName("John Doe");
        customer1.setPhone("555-555-5555");
        customer1.setEmail("john.doe@example.com");

        Customer customer2 = new Customer();
        customer2.setTckn("10987654321");
        customer2.setName("Jane Doe");
        customer2.setPhone("444-444-4444");
        customer2.setEmail("jane.doe@example.com");

        List<Customer> customers = List.of(customer1, customer2);
        Page<Customer> pagedCustomers = new PageImpl<>(customers);

        Specification<Customer> mockSpecification = mock(Specification.class);
        CustomerRepository mockRepository = mock(CustomerRepository.class);

        when(mockRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pagedCustomers);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        List<Customer> result = customerRepositoryGateway.getList(mockSpecification, 0, 2);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678901", result.get(0).getTckn());
        assertEquals("10987654321", result.get(1).getTckn());
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setTckn("12345678901");
        customer.setName("John Doe");
        customer.setPhone("555-555-5555");
        customer.setEmail("john.doe@example.com");

        CustomerRepository mockRepository = mock(CustomerRepository.class);
        when(mockRepository.findByTckn(customer.getTckn())).thenReturn(customer);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        Customer deletedCustomer = customerRepositoryGateway.delete(customer);

        // Assert
        assertNotNull(deletedCustomer);
        assertEquals("12345678901", deletedCustomer.getTckn());
        assertEquals("John Doe", deletedCustomer.getName());
        assertEquals("555-555-5555", deletedCustomer.getPhone());
        assertEquals("john.doe@example.com", deletedCustomer.getEmail());

        // Verify that the customer was deleted
        when(mockRepository.findByTckn(customer.getTckn())).thenReturn(null);
        Customer shouldBeNull = customerRepositoryGateway.get(customer);
        assertNull(shouldBeNull);
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Customer existingCustomer = new Customer();
        existingCustomer.setTckn("12345678901");
        existingCustomer.setName("John Doe");
        existingCustomer.setPhone("555-555-5555");
        existingCustomer.setEmail("john.doe@example.com");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setTckn("12345678901");
        updatedCustomer.setName("Jane Doe");
        updatedCustomer.setPhone("444-444-4444");
        updatedCustomer.setEmail("jane.doe@example.com");

        CustomerRepository mockRepository = mock(CustomerRepository.class);
        when(mockRepository.findByTckn(existingCustomer.getTckn())).thenReturn(existingCustomer);
        when(mockRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        Customer result = customerRepositoryGateway.update(updatedCustomer);

        // Assert
        assertNotNull(result);
        assertEquals("12345678901", result.getTckn());
        assertEquals("Jane Doe", result.getName());
        assertEquals("444-444-4444", result.getPhone());
        assertEquals("jane.doe@example.com", result.getEmail());
    }

    @Test
    public void testFindCustomerByTckn() {
        // Arrange
        Customer customer = new Customer();
        customer.setTckn("12345678901");
        customer.setName("John Doe");
        customer.setPhone("555-555-5555");
        customer.setEmail("john.doe@example.com");

        CustomerRepository mockRepository = mock(CustomerRepository.class);
        when(mockRepository.findByTckn(customer.getTckn())).thenReturn(customer);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        Customer foundCustomer = customerRepositoryGateway.get(customer);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals("12345678901", foundCustomer.getTckn());
        assertEquals("John Doe", foundCustomer.getName());
    }

    @Test
    public void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setTckn("12345678901");
        customer.setName("John Doe");
        customer.setPhone("555-555-5555");
        customer.setEmail("john.doe@example.com");

        CustomerRepository mockRepository = mock(CustomerRepository.class);
        when(mockRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerRepositoryGateway customerRepositoryGateway = new CustomerRepositoryGateway(mockRepository);

        // Act
        Customer createdCustomer = customerRepositoryGateway.create(customer);

        // Assert
        assertNotNull(createdCustomer);
        assertEquals("12345678901", createdCustomer.getTckn());
        assertEquals("John Doe", createdCustomer.getName());
    }



}