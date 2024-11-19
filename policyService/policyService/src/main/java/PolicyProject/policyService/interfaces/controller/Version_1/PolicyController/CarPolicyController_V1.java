package PolicyProject.policyService.interfaces.controller.Version_1.PolicyController;

import PolicyProject.policyService.application.service.Service.PolicyService.CarPolicyService;
import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.*;

import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
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
public class CarPolicyController_V1 {

    private final CarPolicyService carPolicyService;


    @PostMapping
    public ResponseEntity<CreateCarPolicyResponse> createPolicy(@Valid @RequestBody CreateCarPolicyRequest createCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carPolicyService.create
                        (CarPolicyMapper.INSTANCE.createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest)));

    }

    @GetMapping
    public ResponseEntity<GetCarPolicyResponse> getPolicy(@Valid @ModelAttribute GetCarPolicyRequest getCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.get
                        (CarPolicyMapper.INSTANCE.getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest)));
    }

    @GetMapping("/byPlate")
    public ResponseEntity<List<GetCarPolicyResponse>> getPolicyByPlate(@Valid @RequestBody GetCarPolicyWPlateRequest getCarPolicyWPlateRequest)
    {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carPolicyService.getByPlate
                        (CarPolicyMapper.INSTANCE.getCarPolicyRequestWPlateTocarPolicyModel(getCarPolicyWPlateRequest)));
    }



    @PutMapping
    public  ResponseEntity<UpdateCarPolicyResponse> updateCarPolicy(@Valid @RequestBody UpdateCarPolicyRequest updateCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.update
                        (CarPolicyMapper.INSTANCE.updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest)));
    }

    @PutMapping("/accepted")
    public  ResponseEntity<SetCarPolicyStatusResponse> acceptCarPolicy(@Valid @RequestBody SetCarPolicyStatusRequest setCarPolicyStatusRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.acceptCarPolicy
                        (CarPolicyMapper.INSTANCE.setStateCarPolicyRequestToCarPolicyModel(setCarPolicyStatusRequest)));
    }

    @PutMapping("/rejected")
    public  ResponseEntity<SetCarPolicyStatusResponse> rejectCarPolicy(@Valid @RequestBody SetCarPolicyStatusRequest setCarPolicyStatusRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.rejectCarPolicy
                        (CarPolicyMapper.INSTANCE.setStateCarPolicyRequestToCarPolicyModel(setCarPolicyStatusRequest)));
    }



    @GetMapping("/list")
    public ResponseEntity<List<GetCarPolicyResponse>> getPolicies(@Valid @ModelAttribute GetCarPolicyListRequest getCarPolicyListRequest)
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.getList
                        (CarPolicyMapper.INSTANCE.getCarPoliciesToCarPolicyModel(getCarPolicyListRequest)));
    }

    @DeleteMapping
    public ResponseEntity<DeleteCarPolicyResponse> deletePolicy(@Valid @ModelAttribute DeleteCarPolicyRequest deleteCarPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.delete
                        (CarPolicyMapper.INSTANCE.deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest)));
    }


    @GetMapping("/customerPolicies")
    public ResponseEntity<List<GetCustomerCarPoliciesResponse>> customerPolicies(@Valid @RequestBody GetCustomerCarPoliciesRequest getCustomerCarPoliciesRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.get_wPolicy
                        (CarPolicyMapper.INSTANCE.getCustomerCarPoliciesToCarPolicyModel(getCustomerCarPoliciesRequest)));
    }

    @GetMapping("/customerPoliciesBetweenDate")
    public ResponseEntity<List<GetCustomerCarPoliciesResponse>> customerPoliciesBetweenDate(@Valid @RequestBody GetCarPolicyBetweenDateRequest getCarPolicyBetweenDateRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyService.get_Policies_BetweenDate
                        (CarPolicyMapper.INSTANCE.getCustomerCarPoliciesBetweenDateToCarPolicyModel(getCarPolicyBetweenDateRequest)));
    }

    @GetMapping("/totalRecord")
    public ResponseEntity<Integer> getTotalRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(carPolicyService.getTotalRecord());
    }



}
