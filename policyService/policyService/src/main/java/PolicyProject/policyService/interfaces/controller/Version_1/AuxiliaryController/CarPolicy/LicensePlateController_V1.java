package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.CarPolicy;


import PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy.LicensePlateService;
import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/licensePlate")
@RequiredArgsConstructor
public class LicensePlateController_V1 {


    private final LicensePlateService licensePlateService;

    @GetMapping("/WCustomer")
    public ResponseEntity<GetPlateWithCustomerResponse> getWCustomer(
            @Valid @ModelAttribute GetPlateWithCustomerRequest getPlateWithCustomerRequest) {
        GetPlateWithCustomerResponse getPlateWithCustomer = licensePlateService.getWCustomer(
                LicensePlateMapper.INSTANCE.getPlateWithCustomerRequestToLicensePlateModel(getPlateWithCustomerRequest));

        return ResponseEntity.status(HttpStatus.OK).body(getPlateWithCustomer);
    }

}
