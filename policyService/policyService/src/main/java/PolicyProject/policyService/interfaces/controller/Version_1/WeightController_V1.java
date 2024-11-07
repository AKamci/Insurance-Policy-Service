package PolicyProject.policyService.interfaces.controller.Version_1;


import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.application.service.Service.WeightService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.request.WeightRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.CreateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.DeleteCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.UpdateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weight")
@RequiredArgsConstructor
public class WeightController_V1 {

    private final WeightService weightService;


    @PostMapping
    public ResponseEntity<CreateWeightResponse> createWeight(@Valid @RequestBody CreateWeightRequest createWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(weightService.create
                        (WeightsMapper.INSTANCE.CreateWeightRequestToWeightsModel(createWeightRequest)));

    }

    @GetMapping
    public ResponseEntity<GetWeightResponse> getWeight(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.get
                        (WeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
    }

    @PutMapping
    public  ResponseEntity<UpdateWeightResponse> updateWeight(@Valid @RequestBody UpdateWeightRequest updateWeightRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.update
                        (WeightsMapper.INSTANCE.UpdateWeightRequestToWeightsModel(updateWeightRequest)));
    }


    @DeleteMapping
    public ResponseEntity<DeleteWeightResponse> deleteWeight(@Valid @ModelAttribute DeleteWeightRequest deleteWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.delete
                        (WeightsMapper.INSTANCE.DeleteWeightRequestToWeightsModel(deleteWeightRequest)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<GetWeightResponse>> getWeights()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.getList());

    }

    @PutMapping("saveAll")
    public  ResponseEntity<List<UpdateWeightResponse>> updateWeightsAll(@Valid @RequestBody List<UpdateWeightRequest> updateWeightRequestList)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.updateList
                        (WeightsMapper.INSTANCE.UpdateWeightRequestListToWeightsModelList(updateWeightRequestList)));
    }

    @GetMapping("/getPrice")
    public ResponseEntity<GetWeightResponse> getPrice(@Valid @ModelAttribute GetWeightRequest getWeightRequest )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weightService.get
                        (WeightsMapper.INSTANCE.GetWeightRequestToWeightsModel(getWeightRequest)));
    }


}
