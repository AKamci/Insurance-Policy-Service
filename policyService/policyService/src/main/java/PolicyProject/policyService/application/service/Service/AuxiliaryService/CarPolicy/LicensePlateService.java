package PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteLicensePlate;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LicensePlateService {

    private final ExecuteLicensePlate executeLicensePlate;
    private final ObjectValidation objectValidation;

    public GetPlateWithCustomerResponse getWCustomer(LicensePlateModel licensePlateModel) {
        objectValidation.validateModel(licensePlateModel, "licensePlateModel");
        LicensePlateModel licensePlateModelResult = executeLicensePlate.ExecuteGetLicensePlateWithCustomer(licensePlateModel);
        return LicensePlateMapper.INSTANCE.LicensePlateModelToGetPlateWithCustomerResponse(licensePlateModelResult);
    }
}
