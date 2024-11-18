package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.Service.EarthQuakeService;
import PolicyProject.policyService.application.service.Service.PolicyService.HealthPolicyService;
import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.interfaces.mappers.EarthQuakeMapper;
import PolicyProject.policyService.interfaces.mappers.HealthPolicyMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/healthPolicy")
@RequiredArgsConstructor
public class HealthPolicyController_V1 {

    private final HealthPolicyService healthPolicyService;

    @PostMapping
    public ResponseEntity<CreateHealthPolicyResponse> createPolicy(@Valid @RequestBody CreateHealthPolicyRequest createHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(healthPolicyService.create
                        (HealthPolicyMapper.INSTANCE.createHealthPolicyRequestToHealthPolicyModel(createHealthPolicyRequest)));

    }

    @GetMapping
    public ResponseEntity<GetHealthPolicyResponse> getPolicy(@Valid @ModelAttribute GetHealthPolicyRequest getHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.get
                        (HealthPolicyMapper.INSTANCE.getHealthPolicyRequestToHealthPolicyModel(getHealthPolicyRequest)));


    }

    @PutMapping
    public  ResponseEntity<UpdateHealthPolicyResponse> updatePolicy(@Valid @RequestBody UpdateHealthPolicyRequest updateHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.update
                        (HealthPolicyMapper.INSTANCE.updateHealthPolicyRequestToHealthPolicyModel(updateHealthPolicyRequest)));
    }

    @PutMapping("/accepted")
    public  ResponseEntity<SetStateHealthPolicyResponse> acceptPolicy(@Valid @RequestBody SetStateHealthPolicyRequest setStateHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.accept
                        (HealthPolicyMapper.INSTANCE.setStateHealthPolicyRequestToHealthPolicyModel(setStateHealthPolicyRequest)));
    }

    @PutMapping("/rejected")
    public  ResponseEntity<SetStateHealthPolicyResponse> rejectPolicy(@Valid @RequestBody SetStateHealthPolicyRequest setStateHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.reject
                        (HealthPolicyMapper.INSTANCE.setStateHealthPolicyRequestToHealthPolicyModel(setStateHealthPolicyRequest)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<GetHealthPolicyResponse>> getPolicies(@Valid @ModelAttribute GetListHealthPolicyRequest getListHealthPolicyRequest)
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.getList
                        (HealthPolicyMapper.INSTANCE.getHealthPolicyRequestListToHealthPolicyModelList(getListHealthPolicyRequest)));
    }

    @DeleteMapping
    public ResponseEntity<DeleteHealthPolicyResponse> deletePolicy(@Valid @ModelAttribute DeleteHealthPolicyRequest deleteHealthPolicyRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthPolicyService.delete
                        (HealthPolicyMapper.INSTANCE.deleteHealthPolicyRequestToHealthPolicyModel(deleteHealthPolicyRequest)));
    }


    @GetMapping("/totalRecord")
    public ResponseEntity<Integer> getTotalRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(healthPolicyService.getTotalRecord());
    }

}
