package PolicyProject.policyService.interfaces.controller;

import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController
{

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest CreateCustomerRequest )
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.create
                        (CustomerMapper.INSTANCE.createCustomerRequestToCustomerModel(CreateCustomerRequest)));

    }

    @GetMapping
    public ResponseEntity<GetCustomerResponse> getCustomer(@Valid @ModelAttribute GetCustomerRequest getCustomerRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.get
                        (CustomerMapper.INSTANCE.getCustomerRequestToCustomerModel(getCustomerRequest)));
    }

    @PutMapping
    public  ResponseEntity<UpdateCustomerResponse> updateCustomer(@Valid @RequestBody UpdateCustomerRequest UpdateCustomerRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.update
                        (CustomerMapper.INSTANCE.updateCustomerRequestToCustomerModel(UpdateCustomerRequest)));
    }


    @DeleteMapping
    public ResponseEntity<DeleteCustomerResponse> deletePolicy(@Valid @ModelAttribute DeleteCustomerRequest deleteCustomerRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.delete
                        (CustomerMapper.INSTANCE.deleteCustomerRequestToCustomerModel(deleteCustomerRequest)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<GetCustomerResponse>> getCustomers(@Valid @ModelAttribute GetCustomerListRequest getCustomerListRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getList
                        (CustomerMapper.INSTANCE.getCustomerListRequestToCustomerModel(getCustomerListRequest)));
    }

    @GetMapping("/totalRecord")
    public ResponseEntity<Integer> getTotalRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getTotalRecord());
    }



}
