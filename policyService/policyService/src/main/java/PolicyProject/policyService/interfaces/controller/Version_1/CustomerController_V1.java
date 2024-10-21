package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController_V1 {

    private final CustomerService customerService;

    @PostMapping
    public CompletableFuture<ResponseEntity<CreateCustomerResponse>> createCustomer(
            @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.create(
                        CustomerMapper.INSTANCE.createCustomerRequestToCustomerModel(createCustomerRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }


    @GetMapping
    public CompletableFuture<ResponseEntity<GetCustomerResponse>> getCustomer(
            @Valid @ModelAttribute GetCustomerRequest getCustomerRequest) {
        return customerService.get(
                        CustomerMapper.INSTANCE.getCustomerRequestToCustomerModel(getCustomerRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }

    @PutMapping
    public CompletableFuture<ResponseEntity<UpdateCustomerResponse>> updateCustomer(
            @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.update(
                        CustomerMapper.INSTANCE.updateCustomerRequestToCustomerModel(updateCustomerRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }

    @DeleteMapping
    public CompletableFuture<ResponseEntity<DeleteCustomerResponse>> deletePolicy(
            @Valid @ModelAttribute DeleteCustomerRequest deleteCustomerRequest) {
        return customerService.delete(
                        CustomerMapper.INSTANCE.deleteCustomerRequestToCustomerModel(deleteCustomerRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<GetCustomerResponse>>> getCustomers(
            @Valid @ModelAttribute GetCustomerListRequest getCustomerListRequest) {
        return customerService.getList(
                        CustomerMapper.INSTANCE.getCustomerListRequestToCustomerModel(getCustomerListRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }

    @GetMapping("/totalRecord")
    public ResponseEntity<Integer> getTotalRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getTotalRecord());

    }
}
