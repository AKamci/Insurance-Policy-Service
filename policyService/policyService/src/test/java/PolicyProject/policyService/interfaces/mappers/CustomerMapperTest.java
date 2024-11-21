package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.CreateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.DeleteCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.UpdateCustomerResponse;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

public class CustomerMapperTest {
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void testCustomerEntityToCustomerModel() {
        Customer customerEntity = Customer.builder()
                .id(1L)
                .tckn("12345678901")
                .phone("+1234567890")
                .email("john.doe@example.com")
                .build();

        CustomerModel customerModel = mapper.customerEntityToCustomerModel(customerEntity);

        Assertions.assertNotNull(customerModel);
        Assertions.assertEquals(customerEntity.getId(), customerModel.id());
        Assertions.assertEquals(customerEntity.getTckn(), customerModel.tckn());
        Assertions.assertEquals(customerEntity.getPhone(), customerModel.phone());
        Assertions.assertEquals(customerEntity.getEmail(), customerModel.email());
    }
    @Test
    public void testEmptyCustomerEntityToCustomerModel() {
        Customer customerEntity = Customer.builder()
                .id(null)
                .tckn(null)
                .phone(null)
                .email(null)
                .build();

        CustomerModel customerModel = mapper.customerEntityToCustomerModel(customerEntity);

        Assertions.assertNotNull(customerModel);
        Assertions.assertNull(customerModel.id());
        Assertions.assertNull(customerModel.tckn());
        Assertions.assertNull(customerModel.phone());
        Assertions.assertNull(customerModel.email());
    }
    @Test
    public void testCustomerModelToCustomerEntity() {
        CustomerModel customerModel = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                null,
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        Customer customer = mapper.customerModelToCustomerEntity(customerModel);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(customerModel.id(), customer.getId());
        Assertions.assertEquals(customerModel.tckn(), customer.getTckn());
        Assertions.assertEquals(customerModel.phone(), customer.getPhone());
        Assertions.assertEquals(customerModel.email(), customer.getEmail());
    }
    @Test
    public void testEmptyCustomerModelToCustomerEntity() {
        CustomerModel customerModel = new CustomerModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                null,
                0,
                0,
                null
        );

        Customer customer = mapper.customerModelToCustomerEntity(customerModel);

        Assertions.assertNotNull(customer);
        Assertions.assertNull(customer.getId());
        Assertions.assertNull(customer.getTckn());
        Assertions.assertNull(customer.getPhone());
        Assertions.assertNull(customer.getEmail());
    }
    @Test
    public void testCustomersModelToGetCustomerResponse() {
        CustomerModel customerModel1 = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        CustomerModel customerModel2 = new CustomerModel(
                2L,
                "Jane Doe",
                "98765432101",
                "456 Main St",
                "+1987654321",
                "jane.doe@example.com",
                LocalDate.of(1985, 5, 5),
                2,
                2,
                0,
                20,
                null
        );

        List<CustomerModel> customerModels = List.of(customerModel1, customerModel2);

        List<GetCustomerResponse> responses = mapper.customersModelToGetCustomerResponse(customerModels);

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(2, responses.size());

        GetCustomerResponse response1 = responses.get(0);
        Assertions.assertEquals(customerModel1.id(), response1.id());
        Assertions.assertEquals(customerModel1.name(), response1.name());
        Assertions.assertEquals(customerModel1.tckn(), response1.tckn());

        GetCustomerResponse response2 = responses.get(1);
        Assertions.assertEquals(customerModel2.id(), response2.id());
        Assertions.assertEquals(customerModel2.name(), response2.name());
        Assertions.assertEquals(customerModel2.tckn(), response2.tckn());
    }
    @Test
    public void testCustomerEntityListToCustomerModelList() {
        Customer customer1 = Customer.builder()
                .id(1L)
                .tckn("12345678901")
                .phone("+1234567890")
                .email("john.doe@example.com")
                .build();

        Customer customer2 = Customer.builder()
                .id(2L)
                .tckn("98765432101")
                .phone("+1987654321")
                .email("jane.doe@example.com")
                .build();

        List<Customer> customers = List.of(customer1, customer2);

        List<CustomerModel> models = mapper.CustomerEntityListToCustomerModelList(customers);

        Assertions.assertNotNull(models);
        Assertions.assertEquals(2, models.size());

        CustomerModel model1 = models.get(0);
        Assertions.assertEquals(customer1.getId(), model1.id());
        Assertions.assertEquals(customer1.getTckn(), model1.tckn());
        Assertions.assertEquals(customer1.getPhone(), model1.phone());
        Assertions.assertEquals(customer1.getEmail(), model1.email());

        CustomerModel model2 = models.get(1);
        Assertions.assertEquals(customer2.getId(), model2.id());
        Assertions.assertEquals(customer2.getTckn(), model2.tckn());
        Assertions.assertEquals(customer2.getPhone(), model2.phone());
        Assertions.assertEquals(customer2.getEmail(), model2.email());
    }
    @Test
    public void testEmptyCustomerEntityListToCustomerModelList() {
        List<Customer> customers = List.of();

        List<CustomerModel> models = mapper.CustomerEntityListToCustomerModelList(customers);

        Assertions.assertNotNull(models);
        Assertions.assertTrue(models.isEmpty());
    }
    @Test
    public void testEmptyCustomersModelToGetCustomerResponse() {
        List<CustomerModel> customerModels = List.of();

        List<GetCustomerResponse> responses = mapper.customersModelToGetCustomerResponse(customerModels);

        Assertions.assertNotNull(responses);
        Assertions.assertTrue(responses.isEmpty());
    }
    @Test
    public void testCustomerModelToDeleteCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        DeleteCustomerResponse response = mapper.customerModelToDeleteCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(customerModel.tckn(), response.tckn());
    }
    @Test
    public void testEmptyCustomerModelToDeleteCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                null,
                0,
                0,
                null
        );

        DeleteCustomerResponse response = mapper.customerModelToDeleteCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertNull(response.tckn());
    }
    @Test
    public void testCustomerModelToCreateCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        CreateCustomerResponse response = mapper.customerModelToCreateCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(customerModel.id(), response.id());
        Assertions.assertEquals(customerModel.tckn(), response.tckn());
    }
    @Test
    public void testCustomerModelToGetCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        GetCustomerResponse response = mapper.customerModelToGetCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(customerModel.id(), response.id());
        Assertions.assertEquals(customerModel.name(), response.name());
        Assertions.assertEquals(customerModel.tckn(), response.tckn());
        Assertions.assertEquals(customerModel.address(), response.address());
        Assertions.assertEquals(customerModel.phone(), response.phone());
        Assertions.assertEquals(customerModel.email(), response.email());
        Assertions.assertEquals(customerModel.birthDay(), response.birthDay());
        Assertions.assertEquals(customerModel.gender(), response.gender());
    }
    @Test
    public void testEmptyCustomerModelToGetCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                null,
                0,
                0,
                null
        );

        GetCustomerResponse response = mapper.customerModelToGetCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertNull(response.id());
        Assertions.assertNull(response.name());
        Assertions.assertNull(response.tckn());
        Assertions.assertNull(response.address());
        Assertions.assertNull(response.phone());
        Assertions.assertNull(response.email());
        Assertions.assertNull(response.birthDay());
        Assertions.assertNull(response.gender());
    }
    @Test
    public void testCreateCustomerRequestToCustomerModel() {
        CreateCustomerRequest request = new CreateCustomerRequest(
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1
        );

        CustomerModel model = mapper.createCustomerRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(request.name(), model.name());
        Assertions.assertEquals(request.tckn(), model.tckn());
        Assertions.assertEquals(request.address(), model.address());
        Assertions.assertEquals(request.phone(), model.phone());
        Assertions.assertEquals(request.email(), model.email());
        Assertions.assertEquals(request.birthDay(), model.birthDay());
        Assertions.assertEquals(request.gender(), model.gender());
    }
    @Test
    public void testUpdateCustomerRequestToCustomerModel() {
        UpdateCustomerRequest request = new UpdateCustomerRequest(
                "98765432101",
                "Jane Doe",
                "456 Another St",
                "+19876543210",
                "jane.doe@example.com",
                LocalDate.of(1985, 5, 5),
                2
        );

        CustomerModel model = mapper.updateCustomerRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(request.name(), model.name());
        Assertions.assertEquals(request.tckn(), model.tckn());
        Assertions.assertEquals(request.address(), model.address());
        Assertions.assertEquals(request.phone(), model.phone());
        Assertions.assertEquals(request.email(), model.email());
        Assertions.assertEquals(request.birthDay(), model.birthDay());
        Assertions.assertEquals(request.gender(), model.gender());
    }
    @Test
    public void testGetCustomerRequestToCustomerModel() {
        GetCustomerRequest request = new GetCustomerRequest(
                "12345678901"
        );

        CustomerModel model = mapper.getCustomerRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(request.tckn(), model.tckn());
    }
    @Test
    public void testDeleteCustomerRequestToCustomerModel() {
        DeleteCustomerRequest request = new DeleteCustomerRequest(
                "12345678901"
        );

        CustomerModel model = mapper.deleteCustomerRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(request.tckn(), model.tckn());
    }
    @Test
    public void testGetCustomerListRequestToCustomerModel() {
        GetCustomerListRequest request = new GetCustomerListRequest(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                "password123",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                10
        );

        CustomerModel model = mapper.getCustomerListRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(request.id(), model.id());
        Assertions.assertEquals(request.name(), model.name());
        Assertions.assertEquals(request.tckn(), model.tckn());
        Assertions.assertEquals(request.address(), model.address());
        Assertions.assertEquals(request.phone(), model.phone());
        Assertions.assertEquals(request.email(), model.email());
        Assertions.assertEquals(request.birthDay(), model.birthDay());
        Assertions.assertEquals(request.gender(), model.gender());
        Assertions.assertEquals(request.page(), model.page());
        Assertions.assertEquals(request.size(), model.size());
    }
    @Test
    public void testEmptyGetCustomerListRequestToCustomerModel() {
        GetCustomerListRequest request = new GetCustomerListRequest(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                0
        );

        CustomerModel model = mapper.getCustomerListRequestToCustomerModel(request);

        Assertions.assertNotNull(model);
        Assertions.assertNull(model.id());
        Assertions.assertNull(model.name());
        Assertions.assertNull(model.tckn());
        Assertions.assertNull(model.address());
        Assertions.assertNull(model.phone());
        Assertions.assertNull(model.email());
        Assertions.assertNull(model.birthDay());
        Assertions.assertNull(model.gender());
        Assertions.assertEquals(0, model.page());
        Assertions.assertEquals(0, model.size());
    }
    @Test
    public void testCustomerModelToUpdateCustomerResponse() {
        CustomerModel customerModel = new CustomerModel(
                1L,
                "John Doe",
                "12345678901",
                "123 Main St",
                "+1234567890",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                1,
                1,
                0,
                10,
                null
        );

        UpdateCustomerResponse response = mapper.customerModelToUpdateCustomerResponse(customerModel);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(customerModel.tckn(), response.tckn());
    }
}
