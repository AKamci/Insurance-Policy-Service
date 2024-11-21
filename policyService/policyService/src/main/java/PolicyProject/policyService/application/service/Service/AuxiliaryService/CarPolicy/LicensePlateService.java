package PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LicensePlateService {

    private final ExecuteLicensePlate executeLicensePlate;
    private final ObjectValidation objectValidation;

    public GetPlateWithCustomerResponse getWCustomer(LicensePlateModel licensePlateModel) {
        objectValidation.validateModel(licensePlateModel, "licensePlateModel");
        return LicensePlateMapper.INSTANCE.LicensePlateModelToGetPlateWithCustomerResponse
                (executeLicensePlate.ExecuteGetLicensePlateWithCustomer(licensePlateModel));
    }
}
