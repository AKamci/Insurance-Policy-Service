package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.Service.PersonalHealthService;
import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import PolicyProject.policyService.interfaces.mappers.PersonalHealthMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
