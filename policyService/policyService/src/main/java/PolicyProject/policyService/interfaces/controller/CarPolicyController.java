package PolicyProject.policyService.interfaces.controller;

import PolicyProject.policyService.application.service.Service.CarPolicyService;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carPolicy")
@RequiredArgsConstructor
public class CarPolicyController {

    private final CarPolicyService carPolicyService;


    @PostMapping
    public ResponseEntity<CreateCarPolicyResponse> createPolicy(@RequestBody CreateCarPolicyRequest createCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carPolicyService.create
                        (CarPolicyMapper.INSTANCE.createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest)));

    }

    @GetMapping
    public ResponseEntity<GetCarPolicyResponse> getPolicy(@RequestBody GetCarPolicyRequest getCarPolicyRequest)
    {
     return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carPolicyService.get
                        (CarPolicyMapper.INSTANCE.getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest)));
    }

    @PutMapping
    public  ResponseEntity<UpdateCarPolicyResponse> policyRejectionOrApproval(@RequestBody UpdateCarPolicyRequest updateCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.update
                        (CarPolicyMapper.INSTANCE.updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest)));
    }


    @GetMapping("/list")
    public ResponseEntity<List<GetCarPolicyResponse>> getCustomerPolicies()
    {
        return ResponseEntity.status(HttpStatus.OK).body(carPolicyService.getList());
    }

    @DeleteMapping
    public ResponseEntity<DeleteCarPolicyResponse> deletePolicy(@RequestBody DeleteCarPolicyRequest deleteCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.delete
                        (CarPolicyMapper.INSTANCE.deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest)));
    }


    @GetMapping("/customerPolicies")
    public ResponseEntity<List<GetCustomerCarPoliciesResponse>> deletePolicy(@RequestBody @Valid GetCustomerCarPoliciesRequest getCustomerCarPoliciesRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.get_wPolicy
                        (CarPolicyMapper.INSTANCE.getCustomerCarPoliciesToCarPolicyModel(getCustomerCarPoliciesRequest)));
    }

}
