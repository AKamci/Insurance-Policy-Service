package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.HealthPolicy;

import PolicyProject.policyService.application.service.Service.AuxiliaryService.HealthPolicy.PersonalHealthService;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.CreatePersonalHealthRequest;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.CreatePersonalHealthResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/personalHealth")
@RequiredArgsConstructor
public class PersonalHealthController_V1 {

    private final PersonalHealthService personalHealthService;

    @GetMapping("/WCustomer")
    public ResponseEntity<GetPersonalHealthWithCustomerResponse> getWCustomer(
            @Valid @ModelAttribute GetPersonalHealthWithCustomerRequest getPersonalHealthWithCustomerRequest) {
        GetPersonalHealthWithCustomerResponse getPlateWithCustomer = personalHealthService.getWCustomer(
                PersonalHealthMapper.INSTANCE.getGetPersonalHealthWithCustomerRequestToPersonalHealthModel(getPersonalHealthWithCustomerRequest));

        return ResponseEntity.status(HttpStatus.OK).body(getPlateWithCustomer);
    }

    @PostMapping
    public ResponseEntity<CreatePersonalHealthResponse> createPersonalHealth(
            @Valid @RequestBody CreatePersonalHealthRequest createPersonalHealthRequest) {

        CreatePersonalHealthResponse createPersonalHealthResponse = personalHealthService.create(
                PersonalHealthMapper.INSTANCE.createPersonalHealthRequestToPersonalHealthModel(createPersonalHealthRequest));

        return ResponseEntity.status(HttpStatus.OK).body(createPersonalHealthResponse);
    }




}
