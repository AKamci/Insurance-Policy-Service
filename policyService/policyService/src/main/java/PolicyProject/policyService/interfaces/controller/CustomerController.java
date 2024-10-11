package PolicyProject.policyService.interfaces.controller;

import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController
{

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest CreateCustomerRequest )
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.create
                        (CustomerMapper.INSTANCE.createCustomerRequestToCustomerModel(CreateCustomerRequest)));

    }

    @GetMapping
    public ResponseEntity<GetCustomerResponse> getCustomer(@RequestBody GetCustomerRequest GetCustomerRequest)
    {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(customerService.get
                        (CustomerMapper.INSTANCE.getCustomerRequestToCustomerModel(GetCustomerRequest)));
    }

    @PutMapping
    public  ResponseEntity<UpdateCustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest UpdateCustomerRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.update
                        (CustomerMapper.INSTANCE.updateCustomerRequestToCustomerModel(UpdateCustomerRequest)));
    }


    @DeleteMapping
    public ResponseEntity<DeleteCustomerResponse> deletePolicy(@RequestBody DeleteCustomerRequest DeleteCustomerRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.delete
                        (CustomerMapper.INSTANCE.deleteCustomerRequestToCustomerModel(DeleteCustomerRequest)));
    }



    @GetMapping("/list")
    public ResponseEntity<List<GetCustomerResponse>> getCustomerPolicies()
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getList());
    }




}
