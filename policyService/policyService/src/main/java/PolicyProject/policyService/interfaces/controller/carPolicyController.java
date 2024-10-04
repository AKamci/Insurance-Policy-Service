package PolicyProject.policyService.interfaces.controller;

import PolicyProject.policyService.application.service.carPolicyService;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.Mapper;
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
public class carPolicyController {

    private final carPolicyService carPolicyService;


    @PostMapping
    public ResponseEntity<createCarPolicyResponse> createPolicy(@RequestBody createCarPolicyRequest createCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carPolicyService.create
                        (CarPolicyMapper.INSTANCE.createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest)));

    }


    @GetMapping
    public ResponseEntity<getCarPolicyResponse> getPolicy(@RequestBody getCarPolicyRequest getCarPolicyRequest)
    {
     return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carPolicyService.get
                        (CarPolicyMapper.INSTANCE.getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest)));
    }

    @PutMapping
    public  ResponseEntity<updateCarPolicyResponse> policyRejectionOrApproval(@RequestBody updateCarPolicyRequest updateCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.update
                        (CarPolicyMapper.INSTANCE.updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest)));
    }


    @GetMapping("/list")
    public ResponseEntity<List<getCustomerCarPoliciesResponse>> getCustomerPolicies(@RequestBody getCustomerCarPoliciesRequest getCustomerCarPoliciesRequest)
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<deleteCarPolicyResponse> deletePolicy(@RequestBody deleteCarPolicyRequest deleteCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.delete
                        (CarPolicyMapper.INSTANCE.deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest)));
    }


}