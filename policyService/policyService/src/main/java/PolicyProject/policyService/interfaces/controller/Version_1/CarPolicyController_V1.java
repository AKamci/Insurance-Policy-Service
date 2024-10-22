package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
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
public class CarPolicyController_V1 {

    private final ICarPolicyService carPolicyService;


//    @GetMapping("/byPlate")
//    public ResponseEntity<List<GetCarPolicyResponse>> getPolicyByPlate(@Valid @RequestBody GetCarPolicyWPlateRequest getCarPolicyWPlateRequest)
//    {
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .body(carPolicyService.getByPlate
//                        (CarPolicyMapper.INSTANCE.getCarPolicyRequestWPlateTocarPolicyModel(getCarPolicyWPlateRequest)));
//    }
//
//
//    @GetMapping("/customerPolicies")
//    public ResponseEntity<List<GetCustomerCarPoliciesResponse>> customerPolicies(@Valid @RequestBody GetCustomerCarPoliciesRequest getCustomerCarPoliciesRequest)
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(carPolicyService.get_wPolicy
//                        (CarPolicyMapper.INSTANCE.getCustomerCarPoliciesToCarPolicyModel(getCustomerCarPoliciesRequest)));
//    }
//
//    @GetMapping("/customerPoliciesBetweenDate")
//    public ResponseEntity<List<GetCustomerCarPoliciesResponse>> customerPoliciesBetweenDate(@Valid @RequestBody GetCarPolicyBetweenDateRequest getCarPolicyBetweenDateRequest)
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(carPolicyService.get_Policies_BetweenDate
//                        (CarPolicyMapper.INSTANCE.getCustomerCarPoliciesBetweenDateToCarPolicyModel(getCarPolicyBetweenDateRequest)));
//    }

//    @GetMapping("/totalRecord")
//    public ResponseEntity<Integer> getTotalRecord()
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(carPolicyService.getTotalRecord());
//    }



}
