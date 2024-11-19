package PolicyProject.policyService.interfaces.controller.Version_1.WeightsController;


import PolicyProject.policyService.application.service.Service.WeightsService.CarPolicyWeightService;
import PolicyProject.policyService.domain.dto.request.WeightRequest.*;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weight")
@RequiredArgsConstructor
public class CarPolicyWeightController_V1 {

    private final CarPolicyWeightService carPolicyWeightService;


    @PostMapping
    public ResponseEntity<CreateWeightResponse> createWeight(@Valid @RequestBody CreateWeightRequest createWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carPolicyWeightService.create
                        (CarPolicyWeightsMapper.INSTANCE.CreateWeightRequestToWeightsModel(createWeightRequest)));

    }

    @GetMapping
    public ResponseEntity<GetWeightResponse> getWeight(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.get
                        (CarPolicyWeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
    }

    @PutMapping
    public  ResponseEntity<UpdateWeightResponse> updateWeight(@Valid @RequestBody UpdateWeightRequest updateWeightRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.update
                        (CarPolicyWeightsMapper.INSTANCE.UpdateWeightRequestToWeightsModel(updateWeightRequest)));
    }


    @DeleteMapping
    public ResponseEntity<DeleteWeightResponse> deleteWeight(@Valid @ModelAttribute DeleteWeightRequest deleteWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.delete
                        (CarPolicyWeightsMapper.INSTANCE.DeleteWeightRequestToWeightsModel(deleteWeightRequest)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<GetWeightResponse>> getWeights()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.getList());

    }

    @PutMapping("saveAll")
    public  ResponseEntity<List<UpdateWeightResponse>> updateWeightsAll(@Valid @RequestBody List<UpdateWeightRequest> updateWeightRequestList)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.updateList
                        (CarPolicyWeightsMapper.INSTANCE.UpdateWeightRequestListToWeightsModelList(updateWeightRequestList)));
    }

    @GetMapping("/getPrice")
    public ResponseEntity<GetWeightResponse> getPrice(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPolicyWeightService.get
                        (CarPolicyWeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
    }


}
