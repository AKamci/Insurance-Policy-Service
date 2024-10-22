package PolicyProject.policyService.interfaces.controller.Version_1;


import PolicyProject.policyService.application.service.Service.LicensePlateService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.GetCustomerRequest;
import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/licensePlate")
@RequiredArgsConstructor
public class LicensePlateController_V1 {


    private final LicensePlateService licensePlateService;

    @GetMapping("/WCustomer")
    public CompletableFuture<ResponseEntity<GetPlateWithCustomerResponse>> getWCustomer(
            @Valid @ModelAttribute GetPlateWithCustomerRequest getPlateWithCustomerRequest) {
        return licensePlateService.getWCustomer(
                        LicensePlateMapper.INSTANCE.getPlateWithCustomerRequestToLicensePlateModel(getPlateWithCustomerRequest))
                .thenApply(getPlateWithCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(getPlateWithCustomer));
    }

}
