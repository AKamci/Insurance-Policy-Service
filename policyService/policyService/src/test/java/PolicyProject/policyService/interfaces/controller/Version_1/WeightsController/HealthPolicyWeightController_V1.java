//package PolicyProject.policyService.interfaces.controller.Version_1.WeightsController;
//
//import PolicyProject.policyService.application.service.Service.WeightsService.HealthPolicyWeightService;
//import PolicyProject.policyService.domain.dto.request.WeightRequest.CreateWeightRequest;
//import PolicyProject.policyService.domain.dto.request.WeightRequest.DeleteWeightRequest;
//import PolicyProject.policyService.domain.dto.request.WeightRequest.GetWeightRequest;
//import PolicyProject.policyService.domain.dto.request.WeightRequest.UpdateWeightRequest;
//import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
//import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
//import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
//import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
//
//import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
//import PolicyProject.policyService.interfaces.mappers.WeightsMapper.HealthPolicyWeightMapper;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/healthPolicyWeight")
//@RequiredArgsConstructor
//public class HealthPolicyWeightController_V1 {
//
//    private final HealthPolicyWeightService weightService;
//
//
//    @PostMapping
//    public ResponseEntity<CreateWeightResponse> createWeight(@Valid @RequestBody CreateWeightRequest createWeightRequest )
//    {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(weightService.create
//                        (HealthPolicyWeightMapper.INSTANCE.CreateWeightRequestToWeightsModel(createWeightRequest)));
//
//    }
//
//    @GetMapping
//    public ResponseEntity<GetWeightResponse> getWeight(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.get
//                        (CarPolicyWeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
//    }
//
//    @PutMapping
//    public  ResponseEntity<UpdateWeightResponse> updateWeight(@Valid @RequestBody UpdateWeightRequest updateWeightRequest)
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.update
//                        (CarPolicyWeightsMapper.INSTANCE.UpdateWeightRequestToWeightsModel(updateWeightRequest)));
//    }
//
//
//    @DeleteMapping
//    public ResponseEntity<DeleteWeightResponse> deleteWeight(@Valid @ModelAttribute DeleteWeightRequest deleteWeightRequest )
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.delete
//                        (CarPolicyWeightsMapper.INSTANCE.DeleteWeightRequestToWeightsModel(deleteWeightRequest)));
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<List<GetWeightResponse>> getWeights()
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.getList());
//
//    }
//
//    @PutMapping("saveAll")
//    public  ResponseEntity<List<UpdateWeightResponse>> updateWeightsAll(@Valid @RequestBody List<UpdateWeightRequest> updateWeightRequestList)
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.updateList
//                        (CarPolicyWeightsMapper.INSTANCE.UpdateWeightRequestListToWeightsModelList(updateWeightRequestList)));
//    }
//
//    @GetMapping("/getPrice")
//    public ResponseEntity<GetWeightResponse> getPrice(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
//    {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(weightService.get
//                        (CarPolicyWeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
//    }
//
//
//}
