package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.IService.ICustomerService;
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
public class CustomerController_V1
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
    public  ResponseEntity<UpdateCustomerResponse> updateCustomer(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.update
                        (CustomerMapper.INSTANCE.updateCustomerRequestToCustomerModel(updateCustomerRequest)));
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
