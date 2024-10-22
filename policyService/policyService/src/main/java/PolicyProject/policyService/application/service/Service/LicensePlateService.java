package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteLicensePlate;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;


@RequiredArgsConstructor
public class LicensePlateService {

    private final ExecuteLicensePlate executeLicensePlate;
    private final ObjectValidation objectValidation;

    @Async
    public CompletableFuture<GetPlateWithCustomerResponse> getWCustomer(LicensePlateModel licensePlateModel) {
        CompletableFuture<LicensePlateModel> getLicensePlateModelFuture = executeLicensePlate.executeGetWithCustomer(licensePlateModel);
        return getLicensePlateModelFuture.thenApply(LicensePlateMapper.INSTANCE::LicensePlateModelToGetPlateWithCustomerResponse);
    }
}
